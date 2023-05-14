package com.example.rickandmorty.location.data.list.mapper

import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import com.example.rickandmorty.location.domain.list.model.LocationDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationEntityToLocationDomainMapperTest {

    private val mapper = LocationEntityToLocationDomainMapper()
    private val locationEntity = mockk<LocationEntity>()

    @Test
    fun `mapping of input with full fields  should return expected output`() {

        every { locationEntity.id } returns 1
        every { locationEntity.name } returns "Earth"
        every { locationEntity.type } returns "Planet"
        every { locationEntity.dimension } returns "Dimension C-137"

        val result = mapper.invoke(locationEntity)

        val expected = LocationDomain(1, "Earth", "Planet", "Dimension C-137")
        assertEquals(expected, result)
    }
}