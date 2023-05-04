package com.example.rickandmorty.episode.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.episode.domain.list.GetEpisodeListByIdUseCase
import com.example.rickandmorty.episode.domain.list.GetEpisodeListUseCase
import com.example.rickandmorty.episode.domain.list.model.EpisodeFilter
import com.example.rickandmorty.episode.presentation.list.mapper.toEpisodeUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EpisodeListViewModel @Inject constructor(
    private val getEpisodeListUseCase: GetEpisodeListUseCase,
    private val getEpisodeListByIdUseCase: GetEpisodeListByIdUseCase
) : ViewModel() {

    private val searchByFilter = MutableLiveData(EpisodeFilter(""))

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun getFullListEpisode() = searchByFilter.asFlow()
        .debounce(500)
        .flatMapLatest {
            getEpisodeListUseCase(it.name, it.episode)
                .map { pagingData ->
                    pagingData.map { item -> item.toEpisodeUiModel() }
                }
        }
        .cachedIn(viewModelScope)

    suspend fun getListEpisodeById(idList: List<Int>) =
        getEpisodeListByIdUseCase(idList)
            .map { pagingData ->
                pagingData.map { item -> item.toEpisodeUiModel() }
            }.flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)

    fun setSearchByFilter(item: EpisodeFilter) {
        searchByFilter.value = item
    }
}