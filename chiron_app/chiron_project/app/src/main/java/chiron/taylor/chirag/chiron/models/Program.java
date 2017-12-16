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

    public long getId() { return this.id; }

    public String getName() { return this.name; }

    public ArrayList<Workout> getWorkout() { return this.workout; }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkout(ArrayList<Workout> workout) {
        this.workout = workout;
    }

}
