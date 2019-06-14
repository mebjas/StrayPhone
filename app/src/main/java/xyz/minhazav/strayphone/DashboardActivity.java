package xyz.minhazav.strayphone;

import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import xyz.minhazav.strayphone.Database.AppDatabase;
import xyz.minhazav.strayphone.Database.SMSToSlackRelayDataModel;
import xyz.minhazav.strayphone.Relays.SMSDataModel;
import xyz.minhazav.strayphone.Relays.SMSDataProvider;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private final String SMSPermission = "android.permission.READ_SMS";

    private TextView mSMSView;
    private Button btSlackTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btSlackTest = findViewById(R.id.bt_test_slackIntegration);
        mSMSView = findViewById(R.id.tv_smsview);
        renderSMSToScreen();

        //// TODO: remove content below, they are temp
        mETNickName = findViewById(R.id.et_tmp_nickname);
        mETURL = findViewById(R.id.et_tmp_url);
        mBTSaveTMP = findViewById(R.id.bt_tmp_save_to_db);
        mTVDataFromDB = findViewById(R.id.tv_tmp_data_from_db);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            openSettingsActivity();
        } else if (id == R.id.action_temp_open_sms_relay) {
            // TODO(mebjas) - remove this condition and menu, it's temp
            startActivity(new Intent(this, SMSRelaysListActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            openSettingsActivity();
        } else if (id == R.id.nav_share) {
            //// TODO: implement this @priority:low
            showNotImplementedToast();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openSettingsActivity() {
        Intent openSettingsActivityIntent = new Intent(this, SettingsActivity.class);
        startActivity(openSettingsActivityIntent);
    }

    /**
     * TODO: this is a temporary test method. Please remove.
     * @param view
     */
    public void onSlackTestButtonClicked(View view) {
        Toast.makeText(view.getContext(), "Testing Slack API", Toast.LENGTH_LONG).show();

        String dummyWebhookURL = "";
        for (SMSDataModel sms: SMSDataProvider.Instance().getAllSMSInInbox(this)) {
            new AsyncSlack(this, dummyWebhookURL).execute(sms);
        }
    }

    //// TODO: remove this method
    private void renderSMSToScreen() {
        //// Get permission
        //// TODO: add handler if the permission is not granted. Also, take care of do not
        //// this again warning
        if (ContextCompat.checkSelfPermission(getBaseContext(), SMSPermission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{SMSPermission}, REQUEST_CODE_ASK_PERMISSIONS);
        }

        ArrayList<SMSDataModel> smsSoFar = SMSDataProvider.Instance().getAllSMSInInbox(this);
        if (smsSoFar.size() == 0) {
            mSMSView.setText("No SMS in inbox");
        } else {
            mSMSView.setText("");
            for (SMSDataModel sms: smsSoFar) {
                mSMSView.append(sms.body +"\n\n");
            }

            btSlackTest.setVisibility(View.VISIBLE);
        }
    }

    private void showNotImplementedToast() {
        Toast.makeText(
            getApplicationContext(),
            "Not Implemented",
            Toast.LENGTH_LONG).show();
    }

    //// TODO: remove content below
    private EditText mETNickName, mETURL;
    private Button mBTSaveTMP;
    private TextView mTVDataFromDB;

    public void onBTSaveTMPClicked(View view) {
        String nickName = mETNickName.getText().toString();
        String url = mETURL.getText().toString();
        if (url != "" && nickName != "") {
            SMSToSlackRelayDataModel data = new SMSToSlackRelayDataModel();
            data.nickname = nickName;
            data.url = url;
            data.dateAdded = new Date();
            AppDatabase.Instance(this).smsToSlackRelayDAO().insertAll(data);
        }

        mTVDataFromDB.setText("");
        for (SMSToSlackRelayDataModel data: AppDatabase.Instance(this).smsToSlackRelayDAO().getAll()) {
            mTVDataFromDB.append("nickname = " +data.nickname +", url = " +data.url +"\n\n\n");
        }

        //// TODO: get from DB
        //// TODO: print to screen
    }
}
