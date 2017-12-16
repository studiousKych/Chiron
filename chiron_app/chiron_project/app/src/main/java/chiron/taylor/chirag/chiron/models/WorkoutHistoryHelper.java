package chiron.taylor.chirag.chiron.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class WorkoutHistoryHelper extends SQLiteOpenHelper {
    Context context;

    static final int DATABASE_VERSION = 1;

    static final String TABLE = "workoutHistorical";

    static final String CREATE_STATEMENT = "CREATE TABLE workoutHistorical (" +
            "   _id integer primary key autoincrement," +
            "   day text not null," +
            "   name text not null," +
            "   pid integer not null," +
            "   FOREIGN KEY(pid) REFERENCES program(_id)" +
            ")";

    static final String DROP_STATEMENT = "DROP TABLE workoutHistorical";

    public WorkoutHistoryHelper(Context context) {
        super(context, TABLE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_STATEMENT);

        db.execSQL(CREATE_STATEMENT);
    }

    public long createWorkout(long pid, Workout workout) {
        SQLiteDatabase db = this.getWritableDatabase();

        String day = workout.getDay();
        String name = workout.getName();

        ContentValues newValues = new ContentValues();
        newValues.put("day", day);
        newValues.put("name", name);
        newValues.put("pid", pid);

        long id = db.insert(TABLE, null, newValues);

        return id;
    }

    public List<Workout> getWorkouts(long pid) {
        List<Workout> workouts = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "day", "name"};
        String where = "pid = ?";
        String[] whereArgs = new String[] { "" + pid };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "pid DESC");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                long id = cursor.getInt(0);
                String day = cursor.getString(1);
                String name = cursor.getString(2);

                Log.wtf("Workout: ", Long.toString(id));

                Workout workout = new Workout(day, name);
                workout.setId(id);

                workouts.add(workout);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        return workouts;
    }

    public void deleteWorkout(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, "id = ?", new String[] { "" + id });
    }
}

