package com.example.rickandmorty.location.data.detail.mapper

import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationEntityToLocationDetailDomainMapperTest {

    private val mapper = LocationEntityToLocationDetailDomainMapper()
    private val entity = mockk<LocationEntity>()

    @Test
    fun `mapping of input with full fields  should return expected output`() {
        every { entity.id } returns 1
        every { entity.name } returns "Earth"
        every { entity.type } returns "Planet"
        every { entity.dimension } returns "Dimension C-137"
        every { entity.residents } returns listOf(1, 2, 3)

        val result = mapper(entity)


        val expected = LocationDetailDomain(
            1,
            "Earth",
            "Planet",
            "Dimension C-137",
            listOf(1, 2, 3)
        )
        assertEquals(result, expected)
    }
}