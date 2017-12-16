package chiron.taylor.chirag.chiron.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ProgramHelper extends SQLiteOpenHelper {
    Context context;

    static final int DATABASE_VERSION = 1;

    static final String TABLE = "program";

    static final String CREATE_STATEMENT = "CREATE TABLE program (" +
            "   _id integer primary key autoincrement," +
            "   name text not null" +
            "  )";

    static final String DROP_STATEMENT = "DROP TABLE program";

    public ProgramHelper(Context context) {
        super(context, TABLE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersionNum,
                          int newVersionNum) {
        // delete the old database
        db.execSQL(DROP_STATEMENT);

        // re-create the database
        db.execSQL(CREATE_STATEMENT);
    }

    public long createProgram(Program p) {
        SQLiteDatabase db = this.getWritableDatabase();

        String name = p.getName();
        ArrayList<Workout> workouts = p.getWorkout();

        ContentValues newValues = new ContentValues();
        newValues.put("name", name);

        long id = db.insert(TABLE, null, newValues);

        Log.wtf("Program Made:", Long.toString(id));

        return id;
    }

    public Program getProgram(long id) {
        Program program = null;

        Log.wtf("getProgram", "running");

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "name"};
        String where = "_id = ?";
        String[] whereArgs = new String[] { "" + id };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "");

        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();

            String name = cursor.getString(1);
            Log.wtf("Program: ", name);

            program = new Program(id, name);
        }

        return program;
    }

    public boolean deleteProgram(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "id = ?", new String[] { "" + id });

        return (numRows == 1);
    }

    public void deleteAllPrograms() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, "", new String[] { });
    }
}
