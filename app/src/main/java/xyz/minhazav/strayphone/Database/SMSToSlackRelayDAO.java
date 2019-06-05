package xyz.minhazav.strayphone.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SMSToSlackRelayDAO {
    @Query("SELECT * FROM sms_to_slack_config")
    List<SMSToSlackRelayDataModel> getAll();

    @Insert
    void insertAll(SMSToSlackRelayDataModel... dataModels);

    @Query("DELETE FROM sms_to_slack_config where uid = :id")
    void deleteById(int id);
}
