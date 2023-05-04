package com.example.rickandmorty.episode.presentation.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.episode.domain.detail.GetEpisodeDetailUseCase
import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import com.example.rickandmorty.episode.presentation.detail.mapper.EpisodeDetailDomainToEpisodeDetailUiMapper
import com.example.rickandmorty.episode.presentation.detail.model.EpisodeDetailUi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EpisodeDetailViewModel @Inject constructor(
    private val getEpisodeDetailUseCase: GetEpisodeDetailUseCase,
    private val episodeDetailDomainToEpisodeDetailUiMapper: EpisodeDetailDomainToEpisodeDetailUiMapper
) : ViewModel() {

    private val _episodeLiveData = MutableLiveData<EpisodeDetailUi>()
    private val errorLiveData = MutableLiveData<String>()

    val episodeLiveData: LiveData<EpisodeDetailUi>
        get() = _episodeLiveData

    @SuppressLint("CheckResult")
    fun getEpisode(episodeId: Int) {
        getEpisodeDetailUseCase(episodeId)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { item -> handleResults(item) },
                { error -> handleError(error) })
    }

    private fun handleResults(mCharacter: EpisodeDetailDomain) {
        _episodeLiveData.postValue(episodeDetailDomainToEpisodeDetailUiMapper(mCharacter))
    }

    private fun handleError(t: Throwable) {
        errorLiveData.postValue(t.toString())
        Log.e("DATA", t.toString())
    }

}