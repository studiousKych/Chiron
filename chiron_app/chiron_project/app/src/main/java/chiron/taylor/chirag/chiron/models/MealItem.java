package chiron.taylor.chirag.chiron.models;

/**
 * Created by chirag on 15/12/17.
 */

public class MealItem {
    private long id;
    private int serving;
    private FoodItem item;

    public MealItem(long id, int serving, FoodItem item) {
        this.id = id;
        this.serving = serving;
        this.item = item;
    }
}
