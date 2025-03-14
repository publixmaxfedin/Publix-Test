package com.example.publixtest

import retrofit2.http.GET
import retrofit2.http.Query

// https://api.quotable.io/quotes?limit=7
interface ApiService {
    @GET("quotes")
    suspend fun getPharmacy(@Query("limit") limit: Int): List<Quote>?
}