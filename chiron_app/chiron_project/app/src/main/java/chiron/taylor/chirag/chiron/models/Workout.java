package chiron.taylor.chirag.chiron.models;


import java.util.ArrayList;

public class Workout {
    String day;
    String name;
    ArrayList<SetModel> sets;

    public Workout(String day, String name, ArrayList<SetModel> sets) {
        this.day = day;
        this.name = name;
        this.sets = sets;
    }

    public ArrayList<SetModel> getSets() { return this.sets; }
}
