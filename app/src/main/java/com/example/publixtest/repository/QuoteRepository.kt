package com.example.publixtest.repository

import com.example.publixtest.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepository @Inject constructor() {
    fun getQuotes(): Flow<List<Quote>> = flow {
    }
} 