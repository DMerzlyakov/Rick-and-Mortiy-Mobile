package com.example.rickandmorty.location.presentation.list.mapper

import com.example.rickandmorty.location.domain.list.model.LocationDomain
import com.example.rickandmorty.location.presentation.list.model.LocationUi
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationDomainToLocationUiMapperTest {


    private var mockLocationDomain = mockk<LocationDomain>()
    private var mapper = LocationDomainToLocationUiMapper()


    @Test
    fun `mapping of input with full fields  should return expected output`() {
        every { mockLocationDomain.id } returns 1
        every { mockLocationDomain.name } returns "Earth"
        every { mockLocationDomain.type } returns "Planet"
        every { mockLocationDomain.dimension } returns "Dimension C-137"

        val result = mapper(mockLocationDomain)

        val expected = LocationUi(
            1, "Earth",
            "Planet", "Dimension C-137"
        )

        assertEquals(expected, result)
    }
}