package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.data.list.remote.model.CharacterPageDTO
import com.example.rickandmorty.character.data.list.remote.model.CharacterResultsDTO
import com.example.rickandmorty.character.data.list.remote.model.Location
import com.example.rickandmorty.character.data.list.remote.model.Origin
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterPageDtoToCharacterEntityMapperTest {

    private val mockOriginDTO = mockk<Origin>()
    private val mockLocationDTO = mockk<Location>()
    private val mockCharacterPagedDTO = mockk<CharacterPageDTO>()
    private val mockCharacterResultsDTO = mockk<CharacterResultsDTO>()
    private val mapper = CharacterPageDtoToCharacterEntityMapper()

    @Test
    fun `mapping of input with full fields  should return expected output`() {
        every { mockOriginDTO.url } returns "https://rickandmortyapi.com/api/location/1"
        every { mockOriginDTO.name } returns "Earth"
        every { mockLocationDTO.url } returns "https://rickandmortyapi.com/api/location/20"
        every { mockLocationDTO.name } returns "Citadel of Ricks"
        every { mockCharacterResultsDTO.id } returns 1
        every { mockCharacterResultsDTO.name } returns "Rick Sanchez"
        every { mockCharacterResultsDTO.status } returns "Alive"
        every { mockCharacterResultsDTO.species } returns "Human"
        every { mockCharacterResultsDTO.gender } returns "Male"
        every { mockCharacterResultsDTO.image } returns "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        every { mockCharacterResultsDTO.origin } returns mockOriginDTO
        every { mockCharacterResultsDTO.location } returns mockLocationDTO
        every { mockCharacterResultsDTO.episode } returns listOf("https://rickandmortyapi.com/api/episode/1")

        every { mockCharacterPagedDTO.results } returns listOf(
            mockCharacterResultsDTO
        )

        val result = mapper(mockCharacterPagedDTO)

        val expected = listOf(
            CharacterEntity(
                1,
                "Rick Sanchez",
                "Alive",
                "Human",
                "Male",
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                1,
                "Earth",
                20,
                "Citadel of Ricks",
                listOf(1)
            )
        )
        assertEquals(expected, result)
    }
}