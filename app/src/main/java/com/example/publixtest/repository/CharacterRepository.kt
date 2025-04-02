package com.example.publixtest.repository

import com.example.publixtest.Character
import com.example.publixtest.network.CharacterApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterApiService: CharacterApiService
) {
    fun getCharacters(): Flow<List<Character>> = flow {
        // To be implemented by candidate
    }
} 