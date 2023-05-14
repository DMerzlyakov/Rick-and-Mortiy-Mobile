package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterForDetailCacheEntity
import com.example.rickandmorty.character.data.list.remote.model.CharacterResultsDTO
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CharacterResultDtoToCharacterCacheEntityMapperTest {

    private val mockCharacterResultsDTO = mockk<CharacterResultsDTO>()
    private val mapper = CharacterResultDtoToCharacterCacheEntityMapper()

    @Test
    fun `mapping of input with full fields  should return expected output`() {
        every { mockCharacterResultsDTO.id } returns 1
        every { mockCharacterResultsDTO.name } returns "Rick Sanchez"
        every { mockCharacterResultsDTO.status } returns "Alive"
        every { mockCharacterResultsDTO.species } returns "Human"
        every { mockCharacterResultsDTO.gender } returns "Male"
        every { mockCharacterResultsDTO.image } returns "https://rickandmortyapi.com/api/character/avatar/1.jpeg"

        val input = listOf(mockCharacterResultsDTO)
        val result = mapper(input)

        val expected = listOf(
            CharacterForDetailCacheEntity(
                1,
                "Rick Sanchez",
                "Alive",
                "Human",
                "Male",
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            )
        )
        assertEquals(expected, result)
    }

    @Test
    fun `mapping of empty input should return empty output`() {
        val input = emptyList<CharacterResultsDTO>()
        val result = mapper(input)
        assertTrue(result.isEmpty())
    }
}