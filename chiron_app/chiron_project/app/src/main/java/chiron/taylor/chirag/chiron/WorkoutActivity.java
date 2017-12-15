package chiron.taylor.chirag.chiron;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import chiron.taylor.chirag.chiron.models.Program;
import chiron.taylor.chirag.chiron.models.SetModel;
import chiron.taylor.chirag.chiron.models.Workout;

public class WorkoutActivity extends AppCompatActivity {

    ListView list;
    SetAdapter adapter;
    public WorkoutActivity SetListView = null;
    public ArrayList<SetModel> SetListViewValuesArr = new ArrayList<>();
    File f = new File("/sdcard/save_program.bin");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetListView = this;

        setListData();

        Resources res = getResources();
        list = (ListView)findViewById(R.id.setList);

        adapter = new SetAdapter(SetListView, SetListViewValuesArr, res);
        list.setAdapter( adapter );

    }

    public void setListData() {

        Program p = loadSerializedObject(f);
        ArrayList<Workout> workouts = p.getWorkout();
        Workout workout = workouts.get(0);
        ArrayList<SetModel> sets = workout.getSets();

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

