package com.example.rickandmorty.episode.presentation.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.episode.domain.detail.GetEpisodeDetailUseCase
import com.example.rickandmorty.episode.domain.detail.model.EpisodeDetailDomain
import com.example.rickandmorty.episode.presentation.detail.mapper.toEpisodeDetailUi
import com.example.rickandmorty.episode.presentation.detail.model.EpisodeDetailUi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EpisodeDetailViewModel @Inject constructor(
    private val getEpisodeDetailUseCase: GetEpisodeDetailUseCase
): ViewModel() {

    private val _episodeLiveData = MutableLiveData<EpisodeDetailUi>()
    private val errorLiveData = MutableLiveData<String>()

    val episodeLiveData: LiveData<EpisodeDetailUi>
        get() = _episodeLiveData

    fun getCharacterLiveData(): LiveData<EpisodeDetailUi> {
        return episodeLiveData
    }

    @SuppressLint("CheckResult")
    fun getCharacter(episodeId: Int) {
        getEpisodeDetailUseCase(episodeId)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {item -> handleResults(item)},
                {error -> handleError(error)})
    }

    private fun handleResults(mCharacter: EpisodeDetailDomain) {
        _episodeLiveData.postValue(mCharacter.toEpisodeDetailUi())
    }

    private fun handleError(t: Throwable) {
        errorLiveData.postValue(t.toString())
        Log.e("DATA", t.toString())
    }

}