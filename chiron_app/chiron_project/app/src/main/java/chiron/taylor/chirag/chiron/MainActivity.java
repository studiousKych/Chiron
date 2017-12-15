package chiron.taylor.chirag.chiron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements JSONObserver{
    private static String DIET_URL = "http://192.168.0.5:8000/diets/";
    private static String WORK_URL = "http://192.168.0.5:8000/YOUR ROUTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetJSONTask task = new GetJSONTask();
        task.setObserver(this);
        task.execute(new String[] { DIET_URL });

    }

    public void jsonDataReceived(JSONObject json){

    }


}
