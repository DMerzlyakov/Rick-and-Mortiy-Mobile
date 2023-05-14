package com.example.rickandmorty.episode.presentation.list

import androidx.paging.PagingData
import com.example.rickandmorty.Utils.ViewModelHelper
import com.example.rickandmorty.episode.domain.list.GetEpisodeListByIdUseCase
import com.example.rickandmorty.episode.domain.list.GetEpisodeListUseCase
import com.example.rickandmorty.episode.domain.list.model.EpisodeDomain
import com.example.rickandmorty.episode.domain.list.model.EpisodeFilter
import com.example.rickandmorty.episode.presentation.list.mapper.EpisodeDomainToEpisodeUiMapper
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

class EpisodeListViewModelTest : ViewModelHelper(){

    private val episodeDomainList = listOf(
        EpisodeDomain( 1, "Pilot", "December 2, 2013", "S01E01"),
        EpisodeDomain(2, "Pilot", "December 2, 2013", "S01E01")
    )

    private val getEpisodeListUseCase = mockk<GetEpisodeListUseCase>()
    private val getEpisodeListByIdUseCase = mockk<GetEpisodeListByIdUseCase>()
    private val episodeDomainToEpisodeUiMapper = mockk<EpisodeDomainToEpisodeUiMapper>()
    private lateinit var viewModel: EpisodeListViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = EpisodeListViewModel(getEpisodeListUseCase, getEpisodeListByIdUseCase, episodeDomainToEpisodeUiMapper)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `update episode list when searchByFilter value changes, new request is made`() = runTest {

        val testPagingData = flowOf(PagingData.from(episodeDomainList))

        // настраиваем getCharacterListUseCase, чтобы он возвращал testPagingData
        coEvery { getEpisodeListUseCase(any(), any()) } returns testPagingData

        viewModel.setSearchByFilter(EpisodeFilter("initialValue"))
        delay(1000)

        viewModel.getFullListEpisode().take(1).collect{}

        val nInteraction = 5
        for (i in 1..nInteraction){
            viewModel.setSearchByFilter(EpisodeFilter("updatedValue $i"))
            println("$ setSearchFilter number - $i")
            delay(1000)
        }

        coVerify(exactly = nInteraction + 1) { getEpisodeListUseCase(any(), any()) }
    }

}