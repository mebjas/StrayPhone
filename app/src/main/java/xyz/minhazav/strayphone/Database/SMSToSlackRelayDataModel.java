package xyz.minhazav.strayphone.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "sms_to_slack_config")
public class SMSToSlackRelayDataModel {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "dateAdded")
    public Date dateAdded;
}
