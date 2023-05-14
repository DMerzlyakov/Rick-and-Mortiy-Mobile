package com.example.rickandmorty.character.data.list.mapper

import com.example.rickandmorty.character.data.list.local.model.CharacterForDetailCacheEntity
import com.example.rickandmorty.character.domain.list.model.CharacterDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterCacheEntityToCharacterDomainMapperTest {

    private val mockCharacterCacheEntity = mockk<CharacterForDetailCacheEntity>()
    private val mapper = CharacterCacheEntityToCharacterDomainMapper()

    @Test
    fun `mapping of input with full fields should return expected output`() {
        every { mockCharacterCacheEntity.id } returns 1
        every { mockCharacterCacheEntity.name } returns "Rick Sanchez"
        every { mockCharacterCacheEntity.status } returns "Alive"
        every { mockCharacterCacheEntity.species } returns "Human"
        every { mockCharacterCacheEntity.gender } returns "Male"
        every { mockCharacterCacheEntity.urlAvatar } returns "https://rickandmortyapi.com/api/character/avatar/1.jpeg"

        val result = mapper(mockCharacterCacheEntity)

        val expected = CharacterDomain(
            1, "Rick Sanchez", "Alive", "Human", "Male",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )
        assertEquals(expected, result)
    }
}