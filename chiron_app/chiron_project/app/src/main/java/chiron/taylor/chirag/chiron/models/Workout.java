package chiron.taylor.chirag.chiron.models;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Workout{
    long id;
    String day;
    String name;
    ArrayList<SetModel> sets;

    public Workout(String day, String name) {
        this.day = day;
        this.name = name;
    }

    public void setId(long id) { this.id = id; }

    public void setSets(ArrayList<SetModel> sets) { this.sets = sets; }

    public long getId() { return this.id = id; }

    public String getDay() { return this.day; }

    public String getName() { return this.name; }

    public ArrayList<SetModel> getSets() { return this.sets; }


}
