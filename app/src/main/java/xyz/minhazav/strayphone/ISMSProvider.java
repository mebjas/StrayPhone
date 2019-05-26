package xyz.minhazav.strayphone;

import java.util.ArrayList;
import android.app.Activity;

public interface ISMSProvider {
    /**
     * Total count of messages in inbox
     */
    Integer getCount(Activity activity);

    /**
    * Method to get all SMS in inbox;
    * TODO: add an argument that defines max number of messages to read.
    */
    ArrayList<SMSDataModel> getAllSMSInInbox(Activity activity);
}
