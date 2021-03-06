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
            "   _id integer primary key autoincrement," +
            "   name text not null," +
            "   reps integer not null," +
            "   load integer not null," +
            "   rest integer not null," +
            "   url text not null," +
            "   set_order integer not null," +
            "   wid integer not null," +
            "   FOREIGN KEY(wid) REFERENCES workout(_id)" +
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
    public long createSet(long wid, SetModel setModel) {

        String name = setModel.getName();
        int reps = setModel.getReps();
        int load = setModel.getLoad();
        int rest = setModel.getRest();
        String url = setModel.getUrl();
        int order = setModel.getOrder();

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("wid", wid);
        newValues.put("name", name);
        newValues.put("load", load);
        newValues.put("reps", reps);
        newValues.put("rest", rest);
        newValues.put("url", url);
        newValues.put("set_order", order);

        long id = db.insert(TABLE, null, newValues);

        return id;
    }

    public List<SetModel> getSets(long wid) {
        List<SetModel> sets = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "name", "load", "reps", "rest", "url", "set_order"};
        String where = "wid = ?";
        String[] whereArgs = new String[] { "" + wid  };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "set_order");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                String name = cursor.getString(1);
                int load = cursor.getInt(2);
                int reps = cursor.getInt(3);
                int rest = cursor.getInt(4);
                String url = cursor.getString(5);
                int order = cursor.getInt(6);

                SetModel set = new SetModel(name, load, reps, rest, url, order);

                sets.add(set);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        return sets;

    }

    public boolean deleteSet(int order) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "set_order = ?", new String[] { "" + order });

        return (numRows == 1);
    }

    public void deleteAllSets() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, "", new String[] { });
    }

}
