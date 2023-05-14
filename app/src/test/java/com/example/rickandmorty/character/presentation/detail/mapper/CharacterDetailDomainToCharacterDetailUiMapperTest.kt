package com.example.rickandmorty.character.presentation.detail.mapper


import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain
import com.example.rickandmorty.character.domain.detail.model.LocationDetailDomain
import com.example.rickandmorty.character.presentation.detail.model.CharacterDetailUi
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterDetailDomainToCharacterDetailUiMapperTest {

    private val mapper = CharacterDetailDomainToCharacterDetailUiMapper()

    @Test
    fun `mapping of input with full fields  should return expected output`() {
        val mockCharacterDomain = mockk<CharacterDetailDomain>()
        every { mockCharacterDomain.id } returns 1
        every { mockCharacterDomain.name } returns "Rick Sanchez"
        every { mockCharacterDomain.status } returns "Alive"
        every { mockCharacterDomain.species } returns "Human"
        every { mockCharacterDomain.gender } returns "Male"
        every { mockCharacterDomain.origin } returns LocationDetailDomain("Earth", 1)
        every { mockCharacterDomain.urlAvatar } returns "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        every { mockCharacterDomain.location } returns LocationDetailDomain("Citadel of Ricks", 20)
        every { mockCharacterDomain.episodeIdList } returns listOf(1)

        val result = mapper.mapToCharacterDetailUi(mockCharacterDomain)

        val expected = CharacterDetailUi(
            1, "Rick Sanchez", "Alive", "Human", "Male",
            LocationDetailDomain("Earth", 1),
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            LocationDetailDomain("Citadel of Ricks", 20),
            listOf(1)
        )

        assertEquals(expected, result)
    }
}