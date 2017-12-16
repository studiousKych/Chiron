package chiron.taylor.chirag.chiron;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

import chiron.taylor.chirag.chiron.models.Program;
import chiron.taylor.chirag.chiron.models.ProgramHelper;
import chiron.taylor.chirag.chiron.models.SetHelper;
import chiron.taylor.chirag.chiron.models.SetHistoryHelper;
import chiron.taylor.chirag.chiron.models.SetModel;
import chiron.taylor.chirag.chiron.models.Workout;
import chiron.taylor.chirag.chiron.models.WorkoutHelper;
import chiron.taylor.chirag.chiron.models.WorkoutHistoryHelper;

public class WorkoutActivity extends AppCompatActivity {

    ListView list;
    SetAdapter adapter;
    public WorkoutActivity SetListView = null;
    public ArrayList<SetModel> SetListViewValuesArr = new ArrayList<>();
    public ArrayList<SetModel> FinishedSetListArr = new ArrayList<>();

    ProgramHelper programHelper;
    WorkoutHelper workoutHelper;
    SetHelper setHelper;

    WorkoutHistoryHelper workoutHistoryHelper;
    SetHistoryHelper setHistoryHelper;

    long pid;
    long wid;
    Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent intent = getIntent();
        wid = intent.getLongExtra("wid", 0);

        SetListView = this;
        programHelper = new ProgramHelper(this);
        workoutHelper = new WorkoutHelper(this);
        setHelper = new SetHelper(this);

        workoutHistoryHelper = new WorkoutHistoryHelper(this);
        setHistoryHelper = new SetHistoryHelper(this);

        SetData();

        Resources res = getResources();
        list = (ListView)findViewById(R.id.setList);

        adapter = new SetAdapter(SetListView, SetListViewValuesArr, res);
        list.setAdapter( adapter );
    }

    public void SetData() {
        workout = workoutHelper.getWorkout(wid);
        ArrayList<SetModel> sets = (ArrayList<SetModel>) setHelper.getSets(wid);
        SetListViewValuesArr = sets;
    }

    public void onItemClick(int mPosition) {

        SetModel set = (SetModel) SetListViewValuesArr.get(mPosition);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(set.getUrl())));;

    }

    public void setComplete(View view) {

        if (SetListViewValuesArr.size() > 1) {
            int position = ((int) view.getTag());
            SetModel finishedSet = SetListViewValuesArr.get(position);
            SetListViewValuesArr.remove(position);
            adapter.notifyDataSetChanged();
            // Save to Historical
            View parentView = (View) view.getParent();
            TextView loadText = (TextView) parentView.findViewById(R.id.setLoad);
            Button repButton = (Button) parentView.findViewById(R.id.setReps);
            finishedSet.setLoad(Integer.parseInt(loadText.getText().toString()));
            finishedSet.setReps(Integer.parseInt(repButton.getText().toString()));
            FinishedSetListArr.add(finishedSet);
            setHelper.deleteSet(position);
        }
        else {
            int position = ((int) view.getTag());
            SetModel finishedSet = SetListViewValuesArr.get(position);
            // Save to Historical
            View parentView = (View) view.getParent();
            TextView loadText = (TextView) parentView.findViewById(R.id.setLoad);
            Button repButton = (Button) parentView.findViewById(R.id.setReps);
            finishedSet.setLoad(Integer.parseInt(loadText.getText().toString()));
            finishedSet.setReps(Integer.parseInt(repButton.getText().toString()));
            FinishedSetListArr.add(finishedSet);

            long hwid = workoutHistoryHelper.createWorkout(pid, workout);
            workoutHelper.deleteWorkout(wid);
            for (SetModel s : FinishedSetListArr) {
                setHistoryHelper.createSet(hwid, s);
            }
            finish();
        }

    }

    public void repSetter(View view) {

        int position = ((int) view.getTag());
        int rep = SetListViewValuesArr.get(position).getReps();
        Button b = (Button) view;
        int buttonVal = Integer.valueOf(b.getText().toString());

        if (buttonVal == 0) {
            ((Button) view).setText(Integer.toString(rep));
        }
        else {
            ((Button) view).setText(Integer.toString(buttonVal-1));
        }
    }

    public void increment(View view) {
        int position = ((int) view.getTag());
        View parentView = (View) view.getParent();
        TextView loadText = (TextView) parentView.findViewById(R.id.setLoad);
        int loadVal = Integer.parseInt(loadText.getText().toString())+5;
        loadText.setText(Integer.toString(loadVal));
    }

    public void decrement(View view) {
        int position = ((int) view.getTag());
        View parentView = (View) view.getParent();
        TextView loadText = (TextView) parentView.findViewById(R.id.setLoad);
        int loadVal = Integer.parseInt(loadText.getText().toString())-5;
        loadText.setText(Integer.toString(loadVal));
    }
}

