package com.example.rickandmorty.presentation.character_details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CharacterDetailViewModel extends ViewModel {
    public CharacterDetailViewModel(int mId) {

    }

//    private final CharacterRepository repository = new CharacterRepository();
//
//    private final MutableLiveData<Character> characterLiveData = new MutableLiveData<>();
//
//    public LiveData<Character> getCharacterLiveData() {
//        return characterLiveData;
//    }
//
//    public void getCharacter() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getCharacterById(mId)
//                    .onEach(character -> characterLiveData.postValue(character))
//                    .flowOn(Dispatchers.IO)
//                    .collect();
//        }
//    }
}