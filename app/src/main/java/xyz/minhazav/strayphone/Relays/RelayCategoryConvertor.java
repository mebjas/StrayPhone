package xyz.minhazav.strayphone.Relays;

import android.arch.persistence.room.TypeConverter;

public class RelayCategoryConvertor {
    @TypeConverter
    public static String toString(RelayCategory category) {
        return category.toString();
    }

    @TypeConverter
    public static RelayCategory toRelayCategory(String categoryString) {
        return RelayCategory.valueOf(RelayCategory.class, categoryString);
    }
}