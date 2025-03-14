package com.example.publixtest

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    private val _quotes = MutableStateFlow<List<Quote>>(emptyList())
    val quotes: StateFlow<List<Quote>> get() = _quotes

    val loading = mutableStateOf(false)
    val error = mutableStateOf(false)

    fun fetchQuote() {
        loading.value = true
        viewModelScope.launch {
            quoteRepository.getQuotes(7).collect { newQuotes ->
                if (newQuotes.isNullOrEmpty()) {
                    error.value = true
                }
                _quotes.value = newQuotes ?: emptyList()
                loading.value = false
            }
        }
    }
}