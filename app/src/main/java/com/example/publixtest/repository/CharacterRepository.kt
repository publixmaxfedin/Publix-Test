package com.example.publixtest.repository

import com.example.publixtest.Character
import com.example.publixtest.network.CharacterApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterApiService: CharacterApiService
) {
    fun getCharacters(): Flow<List<Character>> = flow {
        emit(
            characterApiService.getCharacters(1)
                .results.map { dto->
                    Character(
                        id = dto.id,
                        name = dto.name,
                        status = dto.status,
                        species = dto.species,
                        image = dto.image
                    )
                }.take(10)
        )
    }.catch { e->
        throw Exception("error ${e.message}")
    }
} 