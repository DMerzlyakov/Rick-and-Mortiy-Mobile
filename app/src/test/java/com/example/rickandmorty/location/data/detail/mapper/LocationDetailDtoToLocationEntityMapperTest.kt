package com.example.rickandmorty.location.data.detail.mapper

import com.example.rickandmorty.location.data.detail.remote.model.LocationDetailDto
import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationDetailDtoToLocationEntityMapperTest {

    private val mapper = LocationDetailDtoToLocationEntityMapper()
    private val mockLocationDetailDto = mockk<LocationDetailDto>()

    @Test
    fun `mapping of input with full fields  should return expected output`() {
        every { mockLocationDetailDto.id } returns 123
        every { mockLocationDetailDto.name } returns "Earth"
        every { mockLocationDetailDto.type } returns "Planet"
        every { mockLocationDetailDto.dimension } returns "Dimension C-137"
        every { mockLocationDetailDto.residents } returns listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2",
            "https://rickandmortyapi.com/api/character/3"
        )

        val result = mapper(mockLocationDetailDto)

        val expected = LocationEntity(
            123,
            "Earth",
            "Planet",
            "Dimension C-137",
            listOf(1, 2, 3)
        )
        assertEquals(expected, result)
    }
}