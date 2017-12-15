package chiron.taylor.chirag.chiron;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MealViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_view);

        Intent intent = getIntent();
        String extra = intent.getStringExtra("text");

        TextView focus = (TextView) findViewById(R.id.focusMeal);
        focus.setText(extra);

    }

}
