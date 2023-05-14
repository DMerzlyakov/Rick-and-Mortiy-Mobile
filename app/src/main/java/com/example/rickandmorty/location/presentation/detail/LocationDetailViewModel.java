package com.example.rickandmorty.location.presentation.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rickandmorty.location.domain.detail.GetLocationDetailUseCase;
import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain;
import com.example.rickandmorty.location.presentation.detail.mapper.LocationDetailDomainToLocationDetailUiMapper;
import com.example.rickandmorty.location.presentation.detail.model.LocationDetailUi;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LocationDetailViewModel extends ViewModel {

    private final GetLocationDetailUseCase getLocationDetailUseCase;
    private final LocationDetailDomainToLocationDetailUiMapper mapper;

    @Inject
    public LocationDetailViewModel(GetLocationDetailUseCase getLocationDetailUseCase, LocationDetailDomainToLocationDetailUiMapper mapper) {
        this.getLocationDetailUseCase = getLocationDetailUseCase;
        this.mapper = mapper;
    }

    private final MutableLiveData<LocationDetailUi> locationLiveData = new MutableLiveData<LocationDetailUi>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public LiveData<LocationDetailUi> getLocationLiveData() {
        return locationLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void getLocation(int LocationId) {
        getLocationDetailUseCase.invoke(LocationId)
                .subscribeOn(Schedulers.io())
                .subscribe(new DisposableSingleObserver<LocationDetailDomain>() {
                    @Override
                    public void onSuccess(LocationDetailDomain locationDetailDomain) {
                        handleResults(locationDetailDomain);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }
                });
    }

    private void handleResults(LocationDetailDomain mLocation) {
        if (mLocation != null) {
            locationLiveData.postValue(mapper.mapToLocationDetailUi(mLocation));
        } else {
            errorLiveData.postValue("Location not found");
        }
    }

    private void handleError(Throwable t) {

        errorLiveData.postValue(t.getLocalizedMessage());
    }

}