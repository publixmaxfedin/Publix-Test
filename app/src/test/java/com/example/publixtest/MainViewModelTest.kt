package com.example.publixtest

import com.example.publixtest.network.CharacterDto
import com.example.publixtest.repository.CharacterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.jetbrains.annotations.ApiStatus.Experimental
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock

import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val mockRepository = mock(CharacterRepository::class.java)

    private val viewModel = MainViewModel(mockRepository)

    @Test
    fun `test refreshing characters updates state with success`() = runBlockingTest {

        val mockCharacters = listOf(Character(1, "Rick", "Alive", "Human", "Main Character"))
        `when`(mockRepository.getCharacters()).thenReturn(flowOf(mockCharacters))

        // Act: Refresh characters
        viewModel.fetchCharacters()

        // Assert: Validate view model state
        val observedData = viewModel.characters.value
        assertNotNull(observedData)
        assertEquals(1, observedData.size)
        assertEquals("Rick", observedData.get(0)?.name)
    }

}