package com.example.publixtest.repository

import com.example.publixtest.network.CharacterApiService
import com.example.publixtest.network.CharacterDto
import com.example.publixtest.network.CharacterResponse
import com.example.publixtest.network.ResponseInfo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CharacterRepositoryTest {

    private val mockApiService = mock<CharacterApiService>()
    private val repository = CharacterRepository(mockApiService)

    @Test
    fun `test correct mapping of name field`() = runBlocking {

        val mockResponse = CharacterResponse(
            info = ResponseInfo(count = 1, pages = 1, next = null, prev = null),
            results = listOf(
                CharacterDto(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Human",
                    type = "Main Character",
                    image = "https://example.com/image.png"
                )
            )
        )
        whenever(mockApiService.getCharacters(page = 1)).thenReturn(mockResponse)


        val characters = repository.getCharacters().first()


        assertNotNull(characters)
        assertEquals(1, characters.size)
        assertEquals("Rick Sanchez", characters.first().name)
    }
}