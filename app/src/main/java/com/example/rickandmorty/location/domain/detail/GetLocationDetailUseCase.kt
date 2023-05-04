package com.example.rickandmorty.location.domain.detail

import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain
import io.reactivex.Single

interface GetLocationDetailUseCase {
    operator fun invoke(mId: Int): Single<LocationDetailDomain>
}