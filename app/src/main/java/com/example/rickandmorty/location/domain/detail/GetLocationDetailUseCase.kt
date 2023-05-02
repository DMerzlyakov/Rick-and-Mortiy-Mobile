package com.example.rickandmorty.location.domain.detail

import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain
import io.reactivex.Observable

interface GetLocationDetailUseCase {
    operator fun invoke(mId: Int): Observable<LocationDetailDomain>
}