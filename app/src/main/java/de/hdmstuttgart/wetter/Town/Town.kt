package de.hdmstuttgart.wetter.Town

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Town (
    @PrimaryKey(autoGenerate = true) val keyID: Int = 0,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    //temperature
    @SerializedName("temperature") var temp: Double? = null,
    // we can add an icon from the folder res.wetter-pictures.
    @ColumnInfo(name = "icon") val icon: String,
)