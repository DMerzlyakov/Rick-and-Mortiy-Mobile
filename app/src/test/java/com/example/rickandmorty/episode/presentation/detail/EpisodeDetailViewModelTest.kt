package com.example.rickandmorty.episode.presentation.detail

import androidx.lifecycle.Observer
import com.example.rickandmorty.Utils.ViewModelHelper
import com.example.rickandmorty.episode.domain.detail.GetEpisodeDetailUseCase
import com.example.rickandmorty.episode.presentation.detail.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import java.time.Duration

class EpisodeDetailViewModelTest: ViewModelHelper() {
    private val getEpisodeDetailUseCase = mockk<GetEpisodeDetailUseCase>()
    private lateinit var viewModel: EpisodeDetailViewModel
    private val episodeDetailDomainToEpisodeDetailUiMapper = mockk<EpisodeDetailDomainToEpisodeDetailUiMapper>()

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = EpisodeDetailViewModel(getEpisodeDetailUseCase, episodeDetailDomainToEpisodeDetailUiMapper)
    }

    @Test
    fun `update error value when error receive`() {
        val errorMessage = "Error message"
        every { getEpisodeDetailUseCase.invoke(any()) } returns Single.error(Throwable(errorMessage))
        val errorObserver = mockk<Observer<String>>()

        viewModel.errorLiveData.observeForever(errorObserver)

        viewModel.getEpisode(1)

        Assertions.assertTimeout(Duration.ofSeconds(1)) {
            Assert.assertEquals(errorMessage, viewModel.errorLiveData.value)
        }
    }

}