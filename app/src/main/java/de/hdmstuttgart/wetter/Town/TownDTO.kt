package de.hdmstuttgart.wetter.Town

import com.google.gson.annotations.SerializedName

//todo: change data structure to: Towns
/**The data structure to provide the data for the saved towns (recent searches) in the
 * Local Storage, then shown in the Main Activity/Fragment list.
 * The name of the town, the description of the trend the weather is takin ("rainy", "sunny")
 * and the temperature trend in Celcius degrees.
 *
 * */
data class TownDTO (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("temperature") val temperature: String,
    //@SerializedName("icon") val icon: String,
){
    fun toDomain(): Town{
        return Town(id = id, name = name, description = description, temperature = temperature)
    }
}