package chiron.taylor.chirag.chiron.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SetHelper extends SQLiteOpenHelper{

    static final int DATABASE_VERSION = 1;

    static final String TABLE = "sets";

    static final String CREATE_STATEMENT = "CREATE TABLE sets (" +
            "   _id integer primary key autoincrement" +
            "   wid integer foreign key" +
            "   name text not null" +
            "   reps integer not null" +
            "   load integer not null" +
            "   rest integer not null" +
            "   url varchar(100) not null" +
            "   order integer not null" +
            ")";

    static final String DROP_STATEMENT = "DROP TABLE sets";

    public SetHelper(Context context) { super(context, TABLE, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersionNum,
                          int newVersionNum) {
        db.execSQL(DROP_STATEMENT);

        db.execSQL(CREATE_STATEMENT);
    }

    // Create Set
    public SetModel createSet(int wid,
                              String name,
                              int reps,
                              int load,
                              int rest,
                              String url,
                              int order) {

        SetModel set = new SetModel(name, reps, load, rest, url, order);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("wid", wid);
        newValues.put("name", name);
        newValues.put("load", load);
        newValues.put("reps", reps);
        newValues.put("rest", rest);
        newValues.put("url", url);

        long id = db.insert(TABLE, null, newValues);

        set.setId(id);

        return set;

    }

    public List<SetModel> getWorkoutsets(long wid) {
        List<SetModel> sets = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "name", "load", "reps", "rest", "url", "order"};
        String where = "wid = ?";
        String[] whereArgs = new String[] { "" + wid  };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "order");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                long id = cursor.getLong(0);
                String name = cursor.getString(1);
                int load = cursor.getInt(2);
                int reps = cursor.getInt(3);
                int rest = cursor.getInt(4);
                String url = cursor.getString(5);
                int order = cursor.getInt(6);

                SetModel set = new SetModel(name, load, reps, rest, url, order);
                set.setId(id);

                sets.add(set);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        return sets;

    }

    public boolean deleteSets(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "_id = ?", new String[] { "" + id });

        return (numRows == 1);
    }

    public void deleteAllSets() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, "", new String[] { });
    }

}
