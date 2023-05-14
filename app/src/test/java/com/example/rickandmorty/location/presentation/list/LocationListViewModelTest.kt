package com.example.rickandmorty.location.presentation.list

import androidx.paging.PagingData
import com.example.rickandmorty.Utils.ViewModelHelper
import com.example.rickandmorty.location.domain.list.GetLocationListUseCase
import com.example.rickandmorty.location.domain.list.model.LocationDomain
import com.example.rickandmorty.location.domain.list.model.LocationFilter
import com.example.rickandmorty.location.presentation.list.mapper.LocationDomainToLocationUiMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocationListViewModelTest: ViewModelHelper() {
    private val locationDomainList = listOf(
        LocationDomain(1, "Earth", "Planet", "Dimension C-137"),
        LocationDomain(2, "Earth", "Planet", "Dimension C-137")
    )

    private val getLocationListUseCase = mockk<GetLocationListUseCase>()
    private val locationDomainToLocationUiMapper = mockk<LocationDomainToLocationUiMapper>()
    private lateinit var viewModel: LocationListViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = LocationListViewModel(getLocationListUseCase, locationDomainToLocationUiMapper)
    }

    @Test
    fun `update location list when searchByFilter value changes, new request is made`() = runTest {

        val testPagingData = flowOf(PagingData.from(locationDomainList))

        coEvery { getLocationListUseCase(any(), any(), any()) } returns testPagingData

        viewModel.setSearchByFilter(LocationFilter("initialValue"))
        delay(1000)

        viewModel.locationFlow.take(1).collect{}

        val nInteraction = 5
        for (i in 1..nInteraction){
            viewModel.setSearchByFilter(LocationFilter("updatedValue $i"))
            println("$ setSearchFilter number - $i")
            delay(1000)
        }

        coVerify(exactly = nInteraction + 1) { getLocationListUseCase(any(), any(), any()) }
    }
}