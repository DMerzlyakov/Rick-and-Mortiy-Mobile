package com.example.rickandmorty.location.presentation.detail.mapper

import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain
import com.example.rickandmorty.location.presentation.detail.model.LocationDetailUi
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationDetailDomainToLocationDetailUiMapperTest {


    private val mapper = LocationDetailDomainToLocationDetailUiMapper()
    private val mockLocationDetailDomain = mockk<LocationDetailDomain>()

    @Test
    fun `mapping of input with full fields  should return expected output`() {
        every { mockLocationDetailDomain.id } returns 1
        every { mockLocationDetailDomain.name } returns "Earth"
        every { mockLocationDetailDomain.type } returns "Planet"
        every { mockLocationDetailDomain.dimension } returns "Dimension C-137"
        every { mockLocationDetailDomain.residents } returns listOf(1, 4)

        val result = mapper.mapToLocationDetailUi(mockLocationDetailDomain)

        val expected = LocationDetailUi(
            1, "Earth", "Planet", "Dimension C-137", listOf(1, 4)
        )
        assertEquals(result, expected)
    }
}