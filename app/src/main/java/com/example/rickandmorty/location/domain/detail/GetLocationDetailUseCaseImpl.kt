package com.example.rickandmorty.location.domain.detail

import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain
import io.reactivex.Observable
import javax.inject.Inject

class GetLocationDetailUseCaseImpl @Inject constructor(
    private val locationDetailRepository: LocationDetailRepository
) : GetLocationDetailUseCase {
    override fun invoke(mId: Int): Observable<LocationDetailDomain> {
        return locationDetailRepository.getLocationDetail(mId)
    }

}