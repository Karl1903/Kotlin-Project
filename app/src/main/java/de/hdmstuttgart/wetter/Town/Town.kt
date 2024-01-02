package de.hdmstuttgart.wetter.Town

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Town (
    @PrimaryKey(autoGenerate = true) val key: Int = 0,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo("name") val name: String,
    //data for now..
    @ColumnInfo("descriptionNow") val descriptionNow: String,
    //temperature
    @ColumnInfo("temperatureNow") val temperatureNow: String,
    @ColumnInfo("windtempoNow") val windtempoNow: String,
    //data for the next day..
    @ColumnInfo("descriptionNextDay") val descriptionNextDay: String,
    @ColumnInfo("temperatureNextDay") val temperatureNextDay: String,
    @ColumnInfo("windtempoNextDay") val windtempoNextDay: String,
    //weather data for the day after the next day..
    @ColumnInfo("descriptionDayAfterNextDay") val descriptionDayAfterNextDay: String,
    @ColumnInfo("temperatureDayAfterNextDay") val temperatureDayAfterNextDay: String,
    @ColumnInfo("windtempoDayAfterNextDay") val windtempoDayAfterNextDay: String,
    // we can add an icon from the folder res.drawable.
    //@SerializedName("icon") val icon: String? = null,
    )