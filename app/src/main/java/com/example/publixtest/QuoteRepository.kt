package com.example.publixtest

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getQuotes(limit: Int): Flow<List<Quote>?> = flow {
        try {
            val quotes = apiService.getPharmacy(limit)
            emit(quotes)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}
