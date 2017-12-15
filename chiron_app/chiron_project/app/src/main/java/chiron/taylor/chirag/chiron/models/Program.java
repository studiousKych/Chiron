package chiron.taylor.chirag.chiron.models;

import java.util.ArrayList;

public class Program {
    private long id;
    private String name;
    private ArrayList<Workout> workout;

    public Program(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() { return this.name; }

    public ArrayList<Workout> getWorkout() { return this.workout; }

}
