package com.example.publixtest.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    fun fetchQuote() {
        viewModelScope.launch {

        }
    }
}