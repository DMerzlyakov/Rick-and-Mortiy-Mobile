package com.example.rickandmorty.episode.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.episode.domain.detail.GetEpisodeDetailUseCase
import com.example.rickandmorty.episode.presentation.detail.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import javax.inject.Inject

class EpisodeDetailsViewModelFactory @Inject constructor(
    private val getEpisodeDetailUseCase: GetEpisodeDetailUseCase,
    private val episodeDetailDomainToEpisodeDetailUiMapper: EpisodeDetailDomainToEpisodeDetailUiMapper
) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpisodeDetailViewModel::class.java)) {
            return EpisodeDetailViewModel(
                getEpisodeDetailUseCase,
                episodeDetailDomainToEpisodeDetailUiMapper
            ) as T
        } else {
            throw RuntimeException("Unknown view model class")

        }

    }
}