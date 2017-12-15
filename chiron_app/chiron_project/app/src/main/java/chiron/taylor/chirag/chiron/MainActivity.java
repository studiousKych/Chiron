package chiron.taylor.chirag.chiron;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import chiron.taylor.chirag.chiron.models.Diet;
import chiron.taylor.chirag.chiron.models.FoodItem;
import chiron.taylor.chirag.chiron.models.MealItem;
import chiron.taylor.chirag.chiron.models.Program;
import chiron.taylor.chirag.chiron.models.SetHelper;
import chiron.taylor.chirag.chiron.models.SetModel;
import chiron.taylor.chirag.chiron.models.StagedMeal;
import chiron.taylor.chirag.chiron.models.Workout;


public class MainActivity extends AppCompatActivity implements JSONObserver{
    private static String DIET_URL = "http://192.168.0.5:8000/diets/?format=json";
    private static String WORK_URL = "http://192.168.0.5:8000/workouts/?format=json";

    private Diet diet;
    private Program program;

    private SetHelper setHelper;
    /*
    private WorkoutHelper workoutHelper;
    private ProgramHelper programHelper;

    private foodItemHelper foodItemHelper;
    private MealItemHealper mealItemHelper;
    private StagedMealHelper stagedMealHelper;
    private DietHelper dietHelper;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setHelper = new SetHelper(this);
        setHelper.deleteAllSets();

        GetJSONTask task = new GetJSONTask();
        task.setObserver(this);
        task.execute(new String[] { DIET_URL });
        task.execute(new String[] { WORK_URL });

    }

    public void viewDiet(View view) {
        //Intent intent = new Intent(this, DietActivity.class);
        //startActivity(intent);
    }

    public void viewWorkout(View view) {
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }

    public void jsonDataReceived(JSONObject json){
        // TODO Checking if adding a type to DietProfile broke anything
        try {
            String type = json.getString("type");

            if (type == "diet") {
                JSONArray stagedMealsArray = json.getJSONArray("staged_meals");
                String diet_name = json.getString("name");
                ArrayList<StagedMeal> stagedMeals = new ArrayList<>();
                // iterates over StagedMeals
                for (int i = 0; i<stagedMealsArray.length(); i++) {
                    JSONObject stagedMealObject = stagedMealsArray.getJSONObject(i);
                    JSONArray mealItemArray = stagedMealObject.getJSONArray("items");
                    ArrayList<MealItem> mealItems = new ArrayList<>();
                    // iterates of MealItems
                    for (int j = 0; j<mealItemArray.length(); j++) {
                        JSONObject mealItemObject = mealItemArray.getJSONObject(j);

                        JSONObject foodItemObject = mealItemObject.getJSONObject("food_item");
                        long food_id = foodItemObject.getLong("id");
                        String name = foodItemObject.getString("name");
                        String food_type = foodItemObject.getString("food_type");
                        String uri = foodItemObject.getString("uri");
                        String custom_recipe = foodItemObject.getString("custom-recipe");
                        float calories = (float) foodItemObject.getDouble("calories");
                        float fats = (float) foodItemObject.getDouble("fats");
                        float carbs = (float) foodItemObject.getDouble("carbs");
                        float protein = (float) foodItemObject.getDouble("protein");
                        float total_servings = (float) foodItemObject.getDouble("total_servings");

                        FoodItem foodItem = new FoodItem(food_id, name, food_type, uri, custom_recipe, calories, fats, carbs, protein, total_servings);

                        long meal_id = mealItemObject.getLong("id");
                        int serving = mealItemObject.getInt("serving");

                        MealItem mealItem = new MealItem(meal_id, serving, foodItem);
                        mealItems.add(mealItem);
                    }
                    long staged_id = stagedMealObject.getLong("id");
                    String day = stagedMealObject.getString("day");
                    int meal_num = stagedMealObject.getInt("meal_num");
                    String meal_type = stagedMealObject.getString("meal_type");
                    StagedMeal stagedMeal = new StagedMeal(staged_id, day, meal_num, meal_type, mealItems);
                    stagedMeals.add(stagedMeal);
                }
                diet = new Diet(diet_name, stagedMeals);
            } else if (type == "workout") {
                JSONArray workoutArray = json.getJSONArray("workout");
                String program_name = json.getString("name");
                ArrayList<Workout> workouts = new ArrayList<>();
                // iterates over workouts
                for (int i = 0; i<workoutArray.length(); i++) {
                    JSONObject workoutObject = workoutArray.getJSONObject(i);
                    JSONArray setArray = workoutObject.getJSONArray("sets");
                    ArrayList<SetModel> sets = new ArrayList<>();
                    for (int j = 0; j<setArray.length(); j++) {
                        JSONObject setObject = setArray.getJSONObject(j);
                        String name = setObject.getString("name");
                        int load = setObject.getInt("load");
                        int reps = setObject.getInt("reps");
                        int rest = setObject.getInt("rest");
                        String url = setObject.getString("url");
                        int order = setObject.getInt("order");

                        SetModel set = new SetModel(name, load, reps, rest, url, order);
                        sets.add(set);
                    }
                    String day = workoutObject.getString("day");
                    String name = workoutObject.getString("name");

                    Workout workout = new Workout(day, name, sets);
                    workouts.add(workout);
                }
                program = new Program(program_name, workouts);

                // Populate Databse

            }
        } catch (JSONException e) {
            Log.wtf("JSONException", e);
        }
    }

    public void saveProgram(Program p) {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("/sdcard/save_program.bin")));
            oos.writeObject(p);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            Log.wtf("Serialization Save Error: ",e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveDiet(Diet d) {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("/sdcard/save_diet.bin")));
            oos.writeObject(d);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            Log.wtf("Serialization Save Error: ",e.getMessage());
            e.printStackTrace();
        }
    }
}
