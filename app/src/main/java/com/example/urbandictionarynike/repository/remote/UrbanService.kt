package com.example.urbandictionarynike.repository.remote

import com.example.urbandictionarynike.model.UrbanResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UrbanService {
    @Headers(HEADER_RAPID_API_KEY)
    @GET("/define")

    suspend fun getDefinitions(@Query("term") term: String): UrbanResponse

    companion object {
        private const val RAPID_API_KEY = "422f3cc52emsh1c45bd753f3e3f1p158908jsn9efb307e67f3"
        const val HEADER_RAPID_API_KEY = "X-RapidAPI-Key: $RAPID_API_KEY"
    }
}
