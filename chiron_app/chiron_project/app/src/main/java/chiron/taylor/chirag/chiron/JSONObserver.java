package chiron.taylor.chirag.chiron;

import org.json.JSONObject;

/**
 * Created by chirag on 15/12/17.
 */

public interface JSONObserver {
    void jsonDataReceived(JSONObject json);
}
