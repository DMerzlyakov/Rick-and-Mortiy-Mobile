package com.example.rickandmorty.location.domain.detail

import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain
import io.reactivex.Observable

interface LocationDetailRepository {

    fun getLocationDetail(mId: Int): Observable<LocationDetailDomain>
}