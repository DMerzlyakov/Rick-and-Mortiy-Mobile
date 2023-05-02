package com.example.rickandmorty.episode.presentation.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmorty.episode.domain.list.GetEpisodeListUseCase
import com.example.rickandmorty.episode.domain.list.model.EpisodeFilter
import com.example.rickandmorty.episode.presentation.list.mapper.toEpisodeUiModel
import com.example.rickandmorty.episode.presentation.list.model.EpisodeUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EpisodeListViewModel @Inject constructor(
    private val getEpisodeListUseCase: GetEpisodeListUseCase
): ViewModel() {
    var episodesFlow: Flow<PagingData<EpisodeUi>>

    private val searchByFilter = MutableLiveData(EpisodeFilter(""))


    init {
        episodesFlow = searchByFilter.asFlow()
            // if user types text too quickly -> filtering intermediate values to avoid excess loads
            .debounce(500)
            .flatMapLatest {
                getEpisodeListUseCase(it.name, it.episode)
                    .map { pagingData ->
                        pagingData.map { item -> item.toEpisodeUiModel() }
                    }
            }
            // always use cacheIn operator for flows returned by Pager. Otherwise exception may be thrown
            // when 1) refreshing/invalidating or 2) subscribing to the flow more than once.
            .cachedIn(viewModelScope)
    }

    fun setSearchByFilter(item: EpisodeFilter) {
        searchByFilter.value = item
        Log.e("EPISODE FILTER", item.toString())
    }
}