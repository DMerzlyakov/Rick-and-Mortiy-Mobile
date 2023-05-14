package com.example.rickandmorty.location.data.list.mapper

import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import com.example.rickandmorty.location.data.list.remote.model.LocationDTO
import com.example.rickandmorty.location.data.list.remote.model.Results
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationDtoToLocationEntityMapperTest {

    private val mapper = LocationDtoToLocationEntityMapper()
    private val locationDTO = mockk<LocationDTO>()
    private val locationResultDTO = mockk<Results>()

    @Test
    fun `mapping of input with full fields  should return expected output`() {
        every { locationResultDTO.id } returns 1
        every { locationResultDTO.name } returns "Earth"
        every { locationResultDTO.type } returns "Planet"
        every { locationResultDTO.dimension } returns "Dimension C-137"
        every { locationResultDTO.residents } returns listOf("https://rickandmortyapi.com/api/character/1")

        every { locationDTO.results } returns listOf(locationResultDTO)

        val result = mapper(locationDTO)


        val expected = listOf(
            LocationEntity(1, "Earth", "Planet", "Dimension C-137", listOf(1))
        )

        assertEquals(result, expected)
    }
}