package de.hdmstuttgart.wetter.Town

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * The class represents the data structure for the data that is shown in the
 * weather fragment and in the main activity's list.
 */

@Entity
data class Town (
    @PrimaryKey(autoGenerate = true) val keyID: Int = 0,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    //temperature
    @SerializedName("temperature") var temp: String? = null,
    // we can add an icon from the folder res.wetter-pictures.
    @SerializedName("icon") val icon: String? = null,
)