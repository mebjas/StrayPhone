package xyz.minhazav.strayphone.Database;

import android.app.Activity;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.TypeConverters;

/**
 * Singleton database class this application
 */
@Database(entities = {SMSToSlackRelayDataModel.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends android.arch.persistence.room.RoomDatabase {

    private static final String DATABASENAME = "strayphone-db";
    private static AppDatabase instance = null;
    private static Object LOCK =  new Object();

    /**
     * Method to get instance
     * @param activity
     * @return Instance of this class
     */
    public static AppDatabase Instance(Activity activity) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = Room.databaseBuilder(
                        activity.getApplicationContext(),
                        AppDatabase.class,
                        DATABASENAME)
                        .build();
            }
        }

        return instance;
    }

    /**
     * DAO object
     * @return
     */
    public abstract SMSToSlackRelayDAO smsToSlackRelayDAO();
}