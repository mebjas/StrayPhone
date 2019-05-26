package xyz.minhazav.strayphone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private LinearLayout llAddEndpoint;
    private EditText etSlackWebhookEndpoint, etSlackWebhookNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        llAddEndpoint = findViewById(R.id.ll_setting_add_endpoint);
        etSlackWebhookEndpoint = findViewById(R.id.et_setting_slack_webhook_url);
        etSlackWebhookNickname = findViewById(R.id.et_setting_slack_nickname);
    }

    public void onAddEndpointClick(View view) {
        llAddEndpoint.setVisibility(View.VISIBLE);
    }

    public void onAddEndpointSaveClick(View view) {
        String nickName = etSlackWebhookNickname.getText().toString();
        String url = etSlackWebhookEndpoint.getText().toString();
        //// TODO: check if this is a valid else throw error.
        //// if correct save this;

        etSlackWebhookEndpoint.setText("");
        etSlackWebhookNickname.setText("");
        llAddEndpoint.setVisibility(View.GONE);
    }

    public void onAddEndpointCancelClick(View view) {
        etSlackWebhookEndpoint.setText("");
        etSlackWebhookNickname.setText("");
        llAddEndpoint.setVisibility(View.GONE);
    }
}
