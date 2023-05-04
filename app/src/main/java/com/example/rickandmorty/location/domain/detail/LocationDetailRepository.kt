package com.example.rickandmorty.location.domain.detail

import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain
import io.reactivex.Single

interface LocationDetailRepository {

    fun getLocationDetail(mId: Int): Single<LocationDetailDomain>
}