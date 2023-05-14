package com.example.rickandmorty.character.presentation.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.character.domain.detail.GetCharacterDetailUseCase;
import com.example.rickandmorty.character.domain.detail.model.CharacterDetailDomain;
import com.example.rickandmorty.character.presentation.detail.mapper.CharacterDetailDomainToCharacterDetailUiMapper;
import com.example.rickandmorty.character.presentation.detail.model.CharacterDetailUi;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CharacterDetailViewModel extends ViewModel {


    private final GetCharacterDetailUseCase getCharacterDetailUseCase;
    private final CharacterDetailDomainToCharacterDetailUiMapper mapper;

    @Inject
    public CharacterDetailViewModel(GetCharacterDetailUseCase getCharacterDetailUseCase, CharacterDetailDomainToCharacterDetailUiMapper mapper) {
        this.getCharacterDetailUseCase = getCharacterDetailUseCase;
        this.mapper = mapper;
    }

    private final MutableLiveData<CharacterDetailUi> characterLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public LiveData<CharacterDetailUi> getCharacterLiveData() {
        return characterLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }


    public void getCharacter(int CharacterId) {
        getCharacterDetailUseCase.invoke(CharacterId)
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<CharacterDetailDomain>() {
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
            errorLiveData.postValue("Character not found");
        }
    }

    private void handleError(Throwable t) {
        errorLiveData.postValue(t.getLocalizedMessage());
    }


}