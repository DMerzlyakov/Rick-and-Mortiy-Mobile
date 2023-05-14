package com.example.rickandmorty.character.data.detail.mapper

import com.example.rickandmorty.character.data.detail.remote.model.CharacterDetailDTO
import com.example.rickandmorty.character.data.detail.remote.model.Location
import com.example.rickandmorty.character.data.detail.remote.model.Origin
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterDetailDtoToCharacterEntityMapperTest {

    private val mockOriginDTO = mockk<Origin>()
    private val mockLocationDTO = mockk<Location>()
    private val mockCharacterDetailDTO = mockk<CharacterDetailDTO>()
    private val mapper = CharacterDetailDtoToCharacterEntityMapper()

    @Test
    fun `mapping of input with full fields  should return expected output`() {

        every { mockOriginDTO.url } returns "https://rickandmortyapi.com/api/location/1"
        every { mockOriginDTO.name } returns "Earth"
        every { mockLocationDTO.url } returns "https://rickandmortyapi.com/api/location/20"
        every { mockLocationDTO.name } returns "Citadel of Ricks"
        every { mockCharacterDetailDTO.id } returns 1
        every { mockCharacterDetailDTO.name } returns "Rick Sanchez"
        every { mockCharacterDetailDTO.status } returns "Alive"
        every { mockCharacterDetailDTO.species } returns "Human"
        every { mockCharacterDetailDTO.gender } returns "Male"
        every { mockCharacterDetailDTO.image } returns "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        every { mockCharacterDetailDTO.origin } returns mockOriginDTO
        every { mockCharacterDetailDTO.location } returns mockLocationDTO
        every { mockCharacterDetailDTO.episode } returns listOf("https://rickandmortyapi.com/api/episode/1")

        val result = mapper(mockCharacterDetailDTO)

        val expected = CharacterEntity(
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
        assertEquals(expected, result)
    }

    @Test
    fun `mapping of input with empty values should return expected output`() {
        every { mockOriginDTO.url } returns ""
        every { mockOriginDTO.name } returns "unknown"
        every { mockLocationDTO.url } returns ""
        every { mockLocationDTO.name } returns "unknown"

        every { mockCharacterDetailDTO.id } returns 1
        every { mockCharacterDetailDTO.name } returns "Rick Sanchez"
        every { mockCharacterDetailDTO.status } returns "Alive"
        every { mockCharacterDetailDTO.species } returns "Human"
        every { mockCharacterDetailDTO.gender } returns "Male"
        every { mockCharacterDetailDTO.image } returns "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        every { mockCharacterDetailDTO.origin } returns mockOriginDTO
        every { mockCharacterDetailDTO.location } returns mockLocationDTO
        every { mockCharacterDetailDTO.episode } returns emptyList()

        val result = mapper(mockCharacterDetailDTO)

        val expected = CharacterEntity(
            1,
            "Rick Sanchez",
            "Alive",
            "Human",
            "Male",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            null,
            "unknown",
            null,
            "unknown",
            emptyList()
        )
        assertEquals(expected, result)
    }
}