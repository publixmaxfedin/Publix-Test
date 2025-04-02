package com.example.publixtest.network

import retrofit2.http.GET
import retrofit2.http.Query

data class CharacterResponse(
    val info: ResponseInfo,
    val results: List<CharacterDto>
)

data class ResponseInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationDto,
    val location: LocationDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class LocationDto(
    val name: String,
    val url: String
)

interface CharacterApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int? = null): CharacterResponse
} 