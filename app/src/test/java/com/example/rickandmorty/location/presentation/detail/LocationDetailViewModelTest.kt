package com.example.rickandmorty.location.presentation.detail

import androidx.lifecycle.Observer
import com.example.rickandmorty.Utils.ViewModelHelper
import com.example.rickandmorty.location.domain.detail.GetLocationDetailUseCase
import com.example.rickandmorty.location.presentation.detail.mapper.LocationDetailDomainToLocationDetailUiMapper
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTimeout
import java.time.Duration

class LocationDetailViewModelTest : ViewModelHelper() {

    private val getLocationDetailUseCase = mockk<GetLocationDetailUseCase>()
    private val mapper = mockk<LocationDetailDomainToLocationDetailUiMapper>()
    private lateinit var viewModel: LocationDetailViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = LocationDetailViewModel(getLocationDetailUseCase, mapper)
    }

    @Test
    fun `update error value when error receive`() {
        val errorMessage = "Error message"
        every { getLocationDetailUseCase.invoke(any()) } returns Single.error(Throwable(errorMessage))
        val errorObserver = mockk<Observer<String>>()

        viewModel.errorLiveData.observeForever(errorObserver)

        viewModel.getLocation(1)

        assertTimeout(Duration.ofSeconds(1)) {
            assertEquals(errorMessage, viewModel.errorLiveData.value)
        }
    }


}