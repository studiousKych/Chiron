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
import java.util.ArrayList;

import chiron.taylor.chirag.chiron.models.Program;
import chiron.taylor.chirag.chiron.models.ProgramHelper;
import chiron.taylor.chirag.chiron.models.SetHelper;
import chiron.taylor.chirag.chiron.models.SetModel;
import chiron.taylor.chirag.chiron.models.Workout;
import chiron.taylor.chirag.chiron.models.WorkoutHelper;

public class WorkoutActivity extends AppCompatActivity {

    ListView list;
    SetAdapter adapter;
    public WorkoutActivity SetListView = null;
    public ArrayList<SetModel> SetListViewValuesArr = new ArrayList<>();

    ProgramHelper programHelper;
    WorkoutHelper workoutHelper;
    SetHelper setHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent intent = getIntent();

        SetListView = this;
        programHelper = new ProgramHelper(this);
        workoutHelper = new WorkoutHelper(this);
        setHelper = new SetHelper(this);

        SetData();

        Resources res = getResources();
        list = (ListView)findViewById(R.id.setList);

        adapter = new SetAdapter(SetListView, SetListViewValuesArr, res);
        list.setAdapter( adapter );
    }

    public void SetData() {
        for (int i = 0; i<4; i++) {
            SetModel set = new SetModel("Bench Press", 225, 10, 60, "https://youtu.be/tuwHzzPdaGc", i);
            SetListViewValuesArr.add(set);
        }
        for (int i = 4; i<8; i++) {
            SetModel set = new SetModel("Military Press", 175, 10, 60, "https://youtu.be/j7ULT6dznNc", i);
            SetListViewValuesArr.add(set);
        }
        for (int i = 8; i<11; i++) {
            SetModel set = new SetModel("DB Bicep Curls", 60, 10, 60, "https://youtu.be/tuwHzzPdaGc", i);
            SetListViewValuesArr.add(set);
        }
        for (int i = 11; i<14; i++) {
            SetModel set = new SetModel("Tricep Extensions", 70, 10, 60, "https://youtu.be/tuwHzzPdaGc", i);
            SetListViewValuesArr.add(set);
        }
    }

    public void onItemClick(int mPosition) {

        SetModel set = (SetModel) SetListViewValuesArr.get(mPosition);

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(set.getUrl())));;

    }

    public void testToast(View view) {

        if (SetListViewValuesArr.size() > 1) {
            int position = ((int) view.getTag());
            SetListViewValuesArr.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(SetListView, "SUCCESS", Toast.LENGTH_LONG).show();
            // Save to Historical
        }
        else {
            // WOOOOOOOOOOOOOOOOOOOO
        }

    }

    public void repTestToast(View view) {

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
        int loadVal = Integer.parseInt(loadText.getText().toString())-5;
        loadText.setText(Integer.toString(loadVal));
    }

    public void decrement(View view) {
        int position = ((int) view.getTag());
        View parentView = (View) view.getParent();
        TextView loadText = (TextView) parentView.findViewById(R.id.setLoad);
        int loadVal = Integer.parseInt(loadText.getText().toString())-5;
        loadText.setText(Integer.toString(loadVal));
    }

    public Program loadSerializedObject(File f) {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            Program p = (Program) ois.readObject();
            return p;
        } catch (Exception e) {
            Log.wtf("Serializtion Read Error: ",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

