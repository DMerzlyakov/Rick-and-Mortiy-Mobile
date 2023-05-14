package com.example.rickandmorty.character.presentation.list.mapper

import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import com.example.rickandmorty.character.presentation.list.model.CharacterUi
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterDomainToCharacterUiModelMapperTest {

    private val mapper = CharacterDomainToCharacterUiModelMapper()

    private val mockCharacterDomain = mockk<CharacterDomain>()


    @Test
    fun `mapping of input with full fields  should return expected output`() {
        every { mockCharacterDomain.id } returns 1
        every { mockCharacterDomain.name } returns "Rick Sanchez"
        every { mockCharacterDomain.status } returns "Alive"
        every { mockCharacterDomain.species } returns "Human"
        every { mockCharacterDomain.gender } returns "Male"
        every { mockCharacterDomain.urlAvatar } returns "https://rickandmortyapi.com/api/character/avatar/1.jpeg"

        val result = mapper(mockCharacterDomain)

        val expected = CharacterUi(
            1,
            "Rick Sanchez",
            "Alive",
            "Human",
            "Male",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )

        assertEquals(expected, result)
    }
}