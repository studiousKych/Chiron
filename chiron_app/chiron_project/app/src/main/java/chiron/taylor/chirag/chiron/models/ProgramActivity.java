package chiron.taylor.chirag.chiron.models;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import chiron.taylor.chirag.chiron.R;
import chiron.taylor.chirag.chiron.WorkoutActivity;

public class ProgramActivity extends AppCompatActivity {

    long pid;
    Context context;
    public ProgramActivity ProgramListView = null;
    ListView mainList = null;
    ArrayAdapter<String> workoutAdapter;
    ArrayList<String> workoutValues;
    ArrayList<Workout> workouts;

    WorkoutHelper workoutHelper;
    WorkoutHistoryHelper workoutHistoryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        // Instantiate Globals
        Intent intent = getIntent();
        pid = intent.getLongExtra("pid", 0);
        context = this;
        ProgramListView = this;
        workoutHelper = new WorkoutHelper(this);
        workoutHistoryHelper = new WorkoutHistoryHelper(this);
        workoutValues = new ArrayList<>();

        // Set Data
        getWorkouts();

        Resources res = getResources();
        mainList = (ListView)findViewById(R.id.workoutList);

        workoutAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, workoutValues);
        mainList.setAdapter( workoutAdapter );

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                long wid = workouts.get(position).getId();
                Intent intent = new Intent(context, WorkoutActivity.class);
                intent.putExtra("wid", wid);
                startActivityForResult(intent, 0);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }

    public void getWorkouts() {
        workouts = (ArrayList<Workout>) workoutHelper.getWorkouts(pid);
        for (Workout w: workouts) {
            workoutValues.add(w.getName()+" "+w.getDay());
        }
    }
}
