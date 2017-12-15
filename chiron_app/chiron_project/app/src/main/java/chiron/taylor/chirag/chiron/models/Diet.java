package chiron.taylor.chirag.chiron.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chirag on 15/12/17.
 */

public class Diet {
    private String name;
    private List<StagedMeal> stagedMeals = new ArrayList<>();

    public Diet(String name, List<StagedMeal> stagedMeals) {
        this.name = name;
        this.stagedMeals = stagedMeals;
    }

    public String getName() { return this.name;}
    public List<StagedMeal> getStagedMeals() { return this.stagedMeals;}

    public void setName(String name) {this.name = name;}
    public void setStagedMeals(List<StagedMeal> stagedMeals) {this.stagedMeals = stagedMeals;}
}
