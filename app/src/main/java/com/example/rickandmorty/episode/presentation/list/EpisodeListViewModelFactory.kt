package com.example.rickandmorty.episode.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.episode.domain.list.GetEpisodeListUseCase
import com.example.rickandmorty.location.domain.list.GetLocationListUseCase
import javax.inject.Inject

class EpisodeListViewModelFactory @Inject constructor(
    private val getEpisodeListUseCase: GetEpisodeListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EpisodeListViewModel::class.java)) {
            EpisodeListViewModel(getEpisodeListUseCase) as T
        } else {
            throw RuntimeException("Unknown view model class")
        }
    }
}