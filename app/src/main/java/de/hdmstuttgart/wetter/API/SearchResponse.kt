package de.hdmstuttgart.wetter.API

import de.hdmstuttgart.wetter.Town.TownDTO
import com.google.gson.annotations.SerializedName
class SearchResponse(
    @SerializedName("search") var search: TownDTO
)
