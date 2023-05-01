package com.example.rickandmorty.character.presentation.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.character.domain.detail.model.CharacterDetail;
import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class CharacterDetailViewModel extends ViewModel {


    private final CharacterDetailRepository characterDetailRepository;

    @Inject
    public CharacterDetailViewModel (CharacterDetailRepository characterDetailRepository) {
        this.characterDetailRepository = characterDetailRepository;

    }

    private final MutableLiveData<CharacterDetail> characterLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    public LiveData<CharacterDetail> getCharacterLiveData() {
        return characterLiveData ;
    }


    public void getCharacter(int CharacterId) {
        characterDetailRepository.getCharacterDetail(CharacterId)
               .subscribeOn(Schedulers.io())
               .subscribe(this::handleResults, this::handleError);
    }


    private void handleResults(CharacterDetail mCharacter) {
        if (mCharacter != null) {

            characterLiveData.postValue(mCharacter);
            Log.e("DATA", "Данные получены" +  mCharacter);
        } else {
            Log.e("DATA", "NO RESULTS FOUND");
        }
    }

    private void handleError(Throwable t) {
        errorLiveData.postValue(t.toString());
        Log.e("DATA", "ERROR IN FETCHING API RESPONSE. Try again");
    }



}