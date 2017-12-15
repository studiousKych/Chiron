package chiron.taylor.chirag.chiron;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import chiron.taylor.chirag.chiron.models.FoodItem;
import chiron.taylor.chirag.chiron.models.MealItem;
import chiron.taylor.chirag.chiron.models.StagedMeal;

public class DietActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter<String> adapter;
    public DietActivity DietListView = null;
    public ArrayList<StagedMeal> MealListViewValuesArr = new ArrayList<>();
    public ArrayList<String> values = new ArrayList<>();
    Context context;
    TextView nextMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        context = this;

        DietListView = this;

        //setDietData();
        setMealVals();

        Resources res = getResources();
        list = (ListView)findViewById(R.id.dietList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        list.setAdapter( adapter );

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;
                String spoofExtra = "";
                switch(position) {
                    case 1:
                        spoofExtra = "Breakfast\n" +
                                "Bacon and Eggs, Greek Yogurt\n" +
                                "Calories: 513\n" +
                                "Fats: 40.59g\n" +
                                "Carbs: 4.97g\n" +
                                "Protein: 30.82g\n";
                        break;
                    case 2:
                        spoofExtra = "Lunch\n" +
                                "BLT Sandwich\n" +
                                "Calories: 1446.17\n" +
                                "Fats: 130.31g\n" +
                                "Carbs: 133.07g\n" +
                                "Protein: 35.504\n";
                        break;
                    case 3:
                        spoofExtra = "Breakfast\n" +
                                "Bacon and Eggs, Greek Yogurt\n" +
                                "Calories: 513\n" +
                                "Fats: 40.59g\n" +
                                "Carbs: 4.97g\n" +
                                "Protein: 30.82g\n";
                        break;
                }


                Intent intent = new Intent(context, MealViewActivity.class);
                intent.putExtra("text", spoofExtra);
                context.startActivity(intent);

            }

        });

        // SPOOF
        String spoofText = "Breakfast\n" +
                "Bacon and Eggs, Greek Yogurt\n" +
                "Calories: 513\n" +
                "Fats: 40.59g\n" +
                "Carbs: 4.97g\n" +
                "Protein: 30.82g\n" +
                "Description: 2 strips bacon, 3 eggs, 100g Greek yogurt";

        nextMeal = (TextView)findViewById(R.id.nextMeal);
        nextMeal.setText(spoofText);

    }

    public void setMealVals() {
        values.add("Breakfast: Bacon and Eggs, Greek Yogurt");
        values.add("Lunch: BLT");
        values.add("Dinner: Ground Beef, Soy Sauce Veggies");
    }

    public void doneSpoof(View view) {
        String newSpoof= "Lunch\n" +
                "BLT Sandwich\n" +
                "Calories: 1446.17\n" +
                "Fats: 130.31g\n" +
                "Carbs: 133.07g\n" +
                "Protein: 35.504\n";
        values.remove(0);
        nextMeal = (TextView)findViewById(R.id.nextMeal);
        nextMeal.setText(newSpoof);
        adapter.notifyDataSetChanged();
    }
}
