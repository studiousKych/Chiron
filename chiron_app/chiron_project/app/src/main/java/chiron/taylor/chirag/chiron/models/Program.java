package chiron.taylor.chirag.chiron.models;

import java.util.ArrayList;

public class Program {
    private String name;
    private ArrayList<Workout> workout;

    public Program(String name, ArrayList<Workout> workout) {
        this.name = name;
        this.workout = workout;
    }

}
