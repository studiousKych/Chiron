/*
package chiron.taylor.chirag.chiron.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ProgramHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;

    static final String TABLE = "program";

    static final String CREATE_STATEMENT = "CREATE TABLE program (" +
            "   _id integer primary key autoincrement" +
            "   name text not null," +
            "  )";

    static final String DROP_STATEMENT = "DROP TABLE program";

    public ProgramHelper(Context context) { super(context, TABLE, null, DATABASE_VERSION); }

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

    public long createProgram(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("name", name);

        long id = db.insert(TABLE, null, newValues);

        return id;
    }

    public Program getProgram(long id) {
        Program program = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "name"};
        String where "_id = ?";
        String[] whereArgs = new String[] { "" + id };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "");

        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();

            String name = cursor.getString(1);

            ArrayList<Workout> workouts = getWorkouts(id);

            program = new Program(name, workouts);
        }

        return program;
    }

    public boolean deleteProgram(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "Id = ?", new String[] { "" + id });
        boolean deleteWorkouts = numRowsDeleteWorkouts(id);

        return (numRows == 1) && deleteWorkouts;
    }

    public void deleteAllPrograms() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, "", new String[] { });
    }
}
*/