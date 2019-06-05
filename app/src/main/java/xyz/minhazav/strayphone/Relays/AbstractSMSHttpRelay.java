package xyz.minhazav.strayphone.Relays;

import android.util.Log;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Abstract class that defines the relay method for sending the request to some HTTP server
 */
public abstract class AbstractSMSHttpRelay implements ISMSHttpRelay {
    /**
     * relays the SMS Data Model to server
     * @param sms SMS Object
     */
    @Override
    public void relay(SMSDataModel sms) {
        Request request = this.createRequest(sms);
        OkHttpClient client = new OkHttpClient();

        try {
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            Log.d("TestLog", body);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * method to create request, not implemented here
     * @param sms SMS Data Model
     * @return request object
     */
    @Override
    public Request createRequest(SMSDataModel sms) {
        throw new UnsupportedOperationException();
    }
}
