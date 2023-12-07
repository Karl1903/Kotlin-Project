package de.hdmstuttgart.wetter.API

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {


    @GET(".")
    suspend fun getSearchResult(
        @Query("s") movieSearch: String,
        @Query("apikey") apikey: String

    ): SearchResponse
}