package com.example.rickandmorty.episode.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.episode.domain.list.GetEpisodeListByIdUseCase
import com.example.rickandmorty.episode.domain.list.GetEpisodeListUseCase
import javax.inject.Inject

class EpisodeListViewModelFactory @Inject constructor(
    private val getEpisodeListUseCase: GetEpisodeListUseCase,
    private val getEpisodeListByIdUseCase: GetEpisodeListByIdUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EpisodeListViewModel::class.java)) {
            EpisodeListViewModel(getEpisodeListUseCase, getEpisodeListByIdUseCase) as T
        } else {
            throw RuntimeException("Unknown view model class")
        }
    }
}