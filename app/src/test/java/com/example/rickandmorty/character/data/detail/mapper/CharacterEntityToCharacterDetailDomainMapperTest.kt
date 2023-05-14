package com.example.rickandmorty.character.data.detail.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import com.example.rickandmorty.character.domain.detail.model.LocationDetailDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterEntityToCharacterDetailDomainMapperTest {

    private val mockCharacterEntity = mockk<CharacterEntity>()
    private val mapper = CharacterEntityToCharacterDetailDomainMapper()

    @Test
    fun `mapping of input with full fields should return expected output`() {
        every { mockCharacterEntity.id } returns 1
        every { mockCharacterEntity.name } returns "Rick Sanchez"
        every { mockCharacterEntity.status } returns "Alive"
        every { mockCharacterEntity.species } returns "Human"
        every { mockCharacterEntity.gender } returns "Male"
        every { mockCharacterEntity.urlAvatar } returns "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        every { mockCharacterEntity.originName } returns "Earth"
        every { mockCharacterEntity.originId } returns 1
        every { mockCharacterEntity.locationName } returns "Citadel of Ricks"
        every { mockCharacterEntity.locationId } returns 20
        every { mockCharacterEntity.episodes } returns listOf(1)

        val result = mapper(mockCharacterEntity)

        val expected = CharacterDetailDomain(
            1, "Rick Sanchez", "Alive", "Human", "Male",
            LocationDetailDomain("Earth", 1),
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            LocationDetailDomain("Citadel of Ricks", 20),
            listOf(1)
        )
        assertEquals(expected, result)
    }

    @Test
    fun `mapping of input with empty values should return expected output`() {
        every { mockCharacterEntity.id } returns 1
        every { mockCharacterEntity.name } returns "Rick Sanchez"
        every { mockCharacterEntity.status } returns "Alive"
        every { mockCharacterEntity.species } returns "Human"
        every { mockCharacterEntity.gender } returns "Male"
        every { mockCharacterEntity.urlAvatar } returns "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        every { mockCharacterEntity.originName } returns "unknown"
        every { mockCharacterEntity.originId } returns null
        every { mockCharacterEntity.locationName } returns "unknown"
        every { mockCharacterEntity.locationId } returns null
        every { mockCharacterEntity.episodes } returns emptyList()

        val result = mapper(mockCharacterEntity)

        val expected = CharacterDetailDomain(
            1, "Rick Sanchez", "Alive", "Human", "Male",
            LocationDetailDomain("unknown", null),
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            LocationDetailDomain("unknown", null),
            emptyList()
        )
        assertEquals(expected, result)

    }
}