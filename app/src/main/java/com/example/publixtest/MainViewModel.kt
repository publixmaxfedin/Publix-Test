package com.example.publixtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    fun fetchQuote() {
        viewModelScope.launch {

        }
    }
}