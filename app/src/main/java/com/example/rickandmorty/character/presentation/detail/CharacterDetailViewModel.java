package com.example.rickandmorty.character.presentation.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.character.domain.detail.GetCharacterDetailUseCase;
import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain;
import com.example.rickandmorty.character.presentation.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper;
import com.example.rickandmorty.character.presentation.detail.model.CharacterDetailUi;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CharacterDetailViewModel extends ViewModel {


    private final GetCharacterDetailUseCase getCharacterDetailUseCase;
    private final CharacterDetailDomainToCharacterDetailUiMapper mapper = new CharacterDetailDomainToCharacterDetailUiMapper();

    @Inject
    public CharacterDetailViewModel (GetCharacterDetailUseCase getCharacterDetailUseCase) {
        this.getCharacterDetailUseCase = getCharacterDetailUseCase;
    }

    private final MutableLiveData<CharacterDetailUi> characterLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    public LiveData<CharacterDetailUi> getCharacterLiveData() {
        return characterLiveData ;
    }
    public Disposable disposable;



    public void getCharacter(int CharacterId) {
        disposable = getCharacterDetailUseCase.invoke(CharacterId)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<CharacterDetailDomain>() {

                    @Override
                    public void onSuccess(CharacterDetailDomain characterDetailDomain) {
                        handleResults(characterDetailDomain);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }

                });
    }


    private void handleResults(CharacterDetailDomain mCharacter) {
        if (mCharacter != null) {
            characterLiveData.postValue(mapper.mapToCharacterDetailUi(mCharacter));
        } else {
            Log.e("DATA", "NO RESULTS FOUND");
        }
    }

    private void handleError(Throwable t) {
        errorLiveData.postValue(t.toString());
        Log.e("DATA", t.toString());
    }



}