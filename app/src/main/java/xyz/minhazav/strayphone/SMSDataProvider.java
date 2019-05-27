package xyz.minhazav.strayphone;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

/**
 * Provider class for SMS implements {@link ISMSProvider} interface
 * TODO: change constructor to load all SMS, but that would make this less reactive
 * Option 2: have a state in this class that stores if all SMS has been loaded to memory.
 * If it's not, then reactively load. @priority low
 *
 * TODO: implement the get count method, and optimize it
 *
 * TODO: this interface may not be required. Remove once obsolete
 */
public class SMSDataProvider implements ISMSProvider {
    private static SMSDataProvider instance = null;
    private String inboxPath = "content://sms/inbox";
    private String subjectColumnName = "subject";
    private String addressColumnName = "address";
    private String bodyColumnName = "body";
    private Uri smsInboxURI = null;

    //// Private constructor
    private SMSDataProvider() {
        this.smsInboxURI = Uri.parse(inboxPath);
    }

    public static SMSDataProvider Instance() {
        if (instance == null) {
            instance = new SMSDataProvider();
        }

        return instance;
    }

    public ArrayList<SMSDataModel> getAllSMSInInbox(Activity activity) {
        ArrayList<SMSDataModel> result = new ArrayList<>();
        Cursor cursor = getSMSCursor(activity);

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                SMSDataModel sms = new SMSDataModel();
                sms.subject = cursor.getString(cursor.getColumnIndex(subjectColumnName));
                sms.address = cursor.getString(cursor.getColumnIndex(addressColumnName));
                sms.body = cursor.getString(cursor.getColumnIndex(bodyColumnName));
                result.add(sms);
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }

        return result;
    }

    public Integer getCount(Activity activity) {
        Cursor cursor = getSMSCursor(activity);
        Integer count = 0;
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                ++count;
            } while (cursor.moveToNext());
        }

        return count;
    }

    private Cursor getSMSCursor(Activity activity) {
        return activity.getContentResolver().query(
                this.smsInboxURI,
                null,
                null,
                null,
                null);
    }
}
