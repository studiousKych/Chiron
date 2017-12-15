package chiron.taylor.chirag.chiron;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by chirag on 15/12/17.
 */

public class GetJSONTask extends AsyncTask<String, Void, JSONObject> {
    private JSONObserver observer;

    public void setObserver(JSONObserver observer) {this.observer = observer;}

    protected JSONObject doInBackground(String... urls) {
        JSONObject dictionary;

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            InputStream inputRaw = conn.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputRaw));

            String content = "";
            String text;

            while ((text = input.readLine()) != null)
                content = content + "\n" + text;

            Log.wtf("ASync", content);

            dictionary = new JSONObject(content);

        } catch (IOException e) {
            e.printStackTrace();
            dictionary = null;
        } catch (JSONException e) {
            e.printStackTrace();
            dictionary = null;
        }

        return dictionary;
    }

    @Override
    protected void onPostExecute(JSONObject dictionary) {observer.jsonDataReceived(dictionary);}
}
