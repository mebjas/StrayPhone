package xyz.minhazav.strayphone;

import java.util.ArrayList;
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
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private String SMSPermission = "android.permission.READ_SMS";
    private TextView mSMSView;

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

        mSMSView = (TextView) findViewById(R.id.tv_smsview);
        renderSMSToScreen();
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
            openSettingActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            openSettingActivity();
        } else if (id == R.id.nav_share) {
            //// TODO: impelemnt this @priority:low
            showNotImplementedToast();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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
        }
    }

    private void openSettingActivity() {
        Intent openSettingIntent = new Intent(this, SettingActivity.class);
        startActivity(openSettingIntent);
    }

    private void showNotImplementedToast() {
        Toast.makeText(
            getApplicationContext(),
            "Not Implemented",
            Toast.LENGTH_LONG).show();
    }
}
