package chiron.taylor.chirag.chiron.models;

public class FoodItem {
    private long id;
    private String name;
    private String food_type;
    private String uri;
    private String custom_recipe;
    private float calories;
    private float fats;
    private float carbs;

    public String getName() {
        return name;
    }

    public String getFood_type() {
        return food_type;
    }

    public String getUri() {
        return uri;
    }

    public String getCustom_recipe() {
        return custom_recipe;
    }

    public float getCalories() {
        return calories;
    }

    public float getFats() {
        return fats;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getProtein() {
        return protein;
    }

    public float getTotal_servings() {
        return total_servings;
    }

    private float protein;
    private float total_servings;

    public FoodItem(long id, String name, String food_type, String uri, String custom_recipe, float calories, float fats, float carbs, float protein, float total_servings) {
        this.id = id;
        this.name = name;
        this.food_type = food_type;
        this.uri = uri;
        this.custom_recipe = custom_recipe;
        this.calories = calories;
        this.fats = fats;
        this.carbs = carbs;
        this.protein = protein;
        this.total_servings = total_servings;
    }
}
