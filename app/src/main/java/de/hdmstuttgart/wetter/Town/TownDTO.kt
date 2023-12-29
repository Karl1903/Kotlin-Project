package de.hdmstuttgart.wetter.Town

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * The class represents the data structure for the data
 * for the weather of a single town that is shown in the
 * weather fragment and in
 * the main activity's (recent searches, local storage) list.
 *  The name of the town, the description of the trend the weather is takin ("rainy", "sunny")
 *  and the temperature trend in Celcius degrees.
 */

@Entity
data class TownDTO (
    @PrimaryKey(autoGenerate = true) val keyID: Int = 0,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    //temperature
    @SerializedName("temperature") var temperature: String? = null,
    // we can add an icon from the folder res.wetter-pictures.
    //@SerializedName("icon") val icon: String? = null,
)