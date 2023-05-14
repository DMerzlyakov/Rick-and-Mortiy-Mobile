package com.example.rickandmorty.location.presentation.detail.mapper;

import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain;
import com.example.rickandmorty.location.presentation.detail.model.LocationDetailUi;

import javax.inject.Inject;

public class LocationDetailDomainToLocationDetailUiMapper {

    @Inject
    public LocationDetailDomainToLocationDetailUiMapper(){}

    public LocationDetailUi mapToLocationDetailUi(LocationDetailDomain item) {
        return new LocationDetailUi(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getDimension(),
                item.getResidents()
        );
    }
}
