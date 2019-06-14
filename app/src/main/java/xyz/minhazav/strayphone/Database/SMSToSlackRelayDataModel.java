package xyz.minhazav.strayphone.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import xyz.minhazav.strayphone.Relays.RelayCategory;

@Entity(tableName = "sms_to_slack_config")
public class SMSToSlackRelayDataModel {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "category")
    public RelayCategory category;

    @ColumnInfo(name = "nickname")
    public String nickname;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "dateAdded")
    public Date dateAdded;

    /**
     * For any more information that cannot be handled by other attributes
     * this column would be used to store serialized key value pair
     * Potentially JSON
     */
    @ColumnInfo(name = "info")
    public String info;
}
