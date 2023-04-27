package com.example.rickandmorty.character.presentation.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.character.di.CharacterDetailRepositoryObject;
import com.example.rickandmorty.character.di.CharacterListRepositoryObject;
import com.example.rickandmorty.character.domain.detail.CharacterDetail;
import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository;
import com.example.rickandmorty.character.domain.list.CharacterListRepository;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CharacterDetailViewModel extends ViewModel {

    private int CharacterId;


    public CharacterDetailViewModel(int mId) {
        CharacterId = mId;
        getCharacter();

        Log.e("ID", "" + mId);
    }

    private final CharacterDetailRepository repository = CharacterDetailRepositoryObject.INSTANCE.getCharacterRepository();
//
    private final MutableLiveData<CharacterDetail> characterLiveData = new MutableLiveData<>();
    public LiveData<CharacterDetail> getCharacterLiveData() {
        return characterLiveData ;
    }
//
    public void getCharacter() {
       repository.getCharacterDetail(CharacterId)
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
        Log.e("DATA", "ERROR IN FETCHING API RESPONSE. Try again");
    }



}