package de.hdmstuttgart.wetter.API

import com.google.gson.annotations.SerializedName
import de.hdmstuttgart.wetter.Town.TownDTO

class SearchResponse(
    @SerializedName("Search") var search: List<TownDTO> )