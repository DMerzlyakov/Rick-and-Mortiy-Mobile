package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterEntityToCharacterDomainMapperTest {

    private val mockEntity = mockk<CharacterEntity>()
    private val mapper = CharacterEntityToCharacterDomainMapper()

    @Test
    fun `mapping of input with full fields should return expected output`() {
        every { mockEntity.id } returns 1
        every { mockEntity.name } returns "Rick Sanchez"
        every { mockEntity.status } returns "Alive"
        every { mockEntity.species } returns "Human"
        every { mockEntity.gender } returns "Male"
        every { mockEntity.urlAvatar } returns "https://rickandmortyapi.com/api/character/avatar/1.jpeg"

        val result = mapper(mockEntity)

        val expected = CharacterDomain(
            1, "Rick Sanchez", "Alive", "Human", "Male",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )
        assertEquals(expected, result)
    }
}