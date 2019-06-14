package xyz.minhazav.strayphone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SMSRelaysListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_relays_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_sms_relay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_relay) {
            openAddSMSRelayActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void openAddSMSRelayActivity() {
        Intent openAddSMSRelayActivityIntent = new Intent(
                this, AddSMSRelayActivity.class);
        startActivity(openAddSMSRelayActivityIntent);
    }
}
