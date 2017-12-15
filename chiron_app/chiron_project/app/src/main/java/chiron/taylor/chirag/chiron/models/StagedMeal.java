package chiron.taylor.chirag.chiron.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chirag on 15/12/17.
 */

public class StagedMeal {
    private long id;
    private String day;
    private int meal_num;
    private String meal_type;
    private List<MealItem> items = new ArrayList<>();
}
