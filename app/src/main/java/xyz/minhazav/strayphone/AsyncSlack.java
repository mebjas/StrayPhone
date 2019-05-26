package xyz.minhazav.strayphone;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncSlack extends AsyncTask<SMSDataModel, Integer, Integer>
{
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String webhookURL = null;

    private Activity activity = null;

    public AsyncSlack(Activity activity, String webhookURL) {
        this.activity = activity;
        this.webhookURL = webhookURL;
    }

    @Override
    protected Integer doInBackground(SMSDataModel... sms) {
        if (sms == null || sms.length == 0 || sms[0] == null || sms[0].body == null) {
            return 0;
        }

        int ret = 0;
        String encodedSlackMessage = "{\"text\":\"" +sms[0].body +"\"}";
        RequestBody requestBody = RequestBody.create(JSON, encodedSlackMessage);
        Request request = new Request.Builder()
                .url(webhookURL)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        try{
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            Log.d("TestLog", body);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return ret;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Toast.makeText(this.activity, "SMS Forwarded to slack", Toast.LENGTH_LONG);
    }
}