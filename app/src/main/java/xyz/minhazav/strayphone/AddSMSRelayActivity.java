package xyz.minhazav.strayphone;

import android.arch.persistence.room.util.StringUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import xyz.minhazav.strayphone.Database.AppDatabase;
import xyz.minhazav.strayphone.Database.SMSToSlackRelayDataModel;
import xyz.minhazav.strayphone.Relays.RelayCategory;

public class AddSMSRelayActivity extends AppCompatActivity {

    private static final int NICKNAME_MAX_LENGTH = 16;
    private EditText etNickname;
    private EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_smsrelay);

        etNickname = findViewById(R.id.et_nickname);
        etUrl = findViewById(R.id.et_url);
    }

    private void showError(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    private boolean isInputValid(String nickname, String url) {
        if ((nickname == null || nickname.trim().isEmpty())
                || (url == null || url.trim().isEmpty())) {
            return false;
        }

        if (nickname.length() > NICKNAME_MAX_LENGTH) {
            // TODO(mebjas) - add support for checking alpha numeric
            return false;
        }

        // TODO(mebjas) - following approach sucks correct it.
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            return  false;
        } catch (Exception ex) {
            throw ex;
        }

        return true;
    }

    private SMSToSlackRelayDataModel createDataModel(String nickname, String url) {
        SMSToSlackRelayDataModel dataModel = new SMSToSlackRelayDataModel();
        dataModel.category = RelayCategory.SMSToSlackRelay;
        dataModel.dateAdded = new Date();
        dataModel.nickname = nickname;
        dataModel.url = url;
        dataModel.info = null;
        return dataModel;
    }

    private void finishAndGoBack() {
        finish();
    }

    public void onAddClicked(View view) {
        String nickname = etNickname.getText().toString();
        String url = etUrl.getText().toString();
        if (!isInputValid(nickname, url)) {
            // TODO(mebjas) - more verbose error messaging
            showError(view, this.getString(R.string.sms_relays_error_message_invalid_input));
            return;
        }

        AppDatabase.Instance(this).smsToSlackRelayDAO().insertAll(
                createDataModel(nickname, url));
        finishAndGoBack();
    }

    public void onCancelClicked(View view) {
        finishAndGoBack();
    }

}
