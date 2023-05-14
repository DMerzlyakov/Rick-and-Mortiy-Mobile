package com.example.rickandmorty.episode.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.episode.domain.list.GetEpisodeListByIdUseCase
import com.example.rickandmorty.episode.domain.list.GetEpisodeListUseCase
import com.example.rickandmorty.episode.presentation.list.mapper.EpisodeDomainToEpisodeUiMapper
import javax.inject.Inject

class EpisodeListViewModelFactory @Inject constructor(
    private val getEpisodeListUseCase: GetEpisodeListUseCase,
    private val getEpisodeListByIdUseCase: GetEpisodeListByIdUseCase,
    private val mapperToUiModel: EpisodeDomainToEpisodeUiMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EpisodeListViewModel::class.java)) {
            EpisodeListViewModel(getEpisodeListUseCase, getEpisodeListByIdUseCase, mapperToUiModel) as T
        } else {
            throw RuntimeException("Unknown view model class")
        }
    }
}