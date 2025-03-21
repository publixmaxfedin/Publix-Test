package com.example.publixtest.network

import retrofit2.http.GET
import retrofit2.http.Query

data class QuoteResponse(
    val count: Int,
    val totalCount: Int,
    val page: Int,
    val totalPages: Int,
    val results: List<QuoteDto>
)

data class QuoteDto(
    val id: String,
    val content: String,
    val author: String,
    val tags: List<String>,
    val authorSlug: String,
    val length: Int,
    val dateAdded: String,
    val dateModified: String
)

interface QuoteApiService {
    @GET("quotes")
    suspend fun getQuotes(@Query("limit") limit: Int = 7): QuoteResponse
} 