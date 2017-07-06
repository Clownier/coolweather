package clown.com.coolweather.app.util;

/**
 * Created by Clown on 2017/7/7.
 */

public interface HttpCallbackListener {
    void onFinish (String response);
    void OnError (Exception e);
}
