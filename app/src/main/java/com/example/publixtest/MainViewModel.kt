package com.example.publixtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.publixtest.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()

    // Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Error state
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        _isLoading.value = true
        viewModelScope.launch {
            characterRepository.getCharacters()
                .onEach {
                    _characters.value = it
                }
                .catch { e ->
                    _error.value = e.message
                }
                .onCompletion {
                    _isLoading.value = false
                }.collect()
        }
    }
}