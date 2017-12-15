package chiron.taylor.chirag.chiron;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    EditText address;
    EditText username;
    EditText password;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address = (EditText) findViewById(R.id.address);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);

        submit = (Button) findViewById(R.id.submit);
    }

    public void login(View view) throws IOException {
        String server_address = address.getText().toString();
        String server_user = username.getText().toString();
        String server_pass = password.getText().toString();

        URL server_endpoint = new URL(server_address);

        String userData = "username=%s".format(server_user);
        String passData = "password=%s".format(server_pass);

        HttpURLConnection server_connection = (HttpURLConnection) server_endpoint.openConnection();

        server_connection.setRequestMethod("POST");
        server_connection.setDoOutput(true);

        enableStrictMode();
        OutputStream output_stream = server_connection.getOutputStream();

        output_stream.write(userData.getBytes());
        output_stream.write(passData.getBytes());
        output_stream.close();

        int response_code = server_connection.getResponseCode();
        Log.wtf("main", Integer.toString(response_code));
//        if (server_connection.getResponseCode() == 200)
    }

    public void enableStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }
}
