package com.example.rickandmorty.location.presentation.detail;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.location.domain.detail.GetLocationDetailUseCase;
import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain;
import com.example.rickandmorty.location.presentation.detail.mapper.LocationDetailDomainToLocationDetailUiMapper;
import com.example.rickandmorty.location.presentation.detail.model.LocationDetailUi;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class LocationDetailViewModel extends ViewModel {


    private final GetLocationDetailUseCase getLocationDetailUseCase;

    private final LocationDetailDomainToLocationDetailUiMapper mapper = new LocationDetailDomainToLocationDetailUiMapper();


    @Inject
    public LocationDetailViewModel(GetLocationDetailUseCase getLocationDetailUseCase) {
        this.getLocationDetailUseCase = getLocationDetailUseCase;

    }

    private final MutableLiveData<LocationDetailUi> locationLiveData = new MutableLiveData<LocationDetailUi>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public LiveData<LocationDetailUi> getLocationLiveData() {
        return locationLiveData;
    }


    @SuppressLint("CheckResult")
    public void getLocation(int LocationId) {
        getLocationDetailUseCase.invoke(LocationId)
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResults, this::handleError);
    }


    private void handleResults(LocationDetailDomain mLocation) {
        if (mLocation != null) {
            locationLiveData.postValue(mapper.mapToLocationDetailUi(mLocation));
        } else {
            errorLiveData.postValue("No data");
        }
    }

    private void handleError(Throwable t) {
        errorLiveData.postValue(t.toString());
    }


}