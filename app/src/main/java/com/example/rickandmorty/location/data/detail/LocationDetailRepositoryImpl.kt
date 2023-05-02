package com.example.rickandmorty.location.data.detail

import com.example.rickandmorty.location.data.detail.local.LocationDetailDao
import com.example.rickandmorty.location.data.detail.remote.LocationDetailApi
import com.example.rickandmorty.location.data.detail.local.model.LocationEntity
import com.example.rickandmorty.location.domain.detail.LocationDetailRepository
import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain
import io.reactivex.Observable
import javax.inject.Inject

class LocationDetailRepositoryImpl @Inject constructor(
    private val locationDetailApi: LocationDetailApi,
    private val locationDetailDao: LocationDetailDao
) : LocationDetailRepository {
    override fun getLocationDetail(mId: Int): Observable<LocationDetailDomain> {

        return getLocationDetailByRemote(mId).flatMap {
            locationDetailDao.update(it).andThen(Observable.just(LocationDetailDomain(it.id, it.name, it.type, it.dimension, it.residents)))
        }.onErrorResumeNext { _: Throwable ->
            locationDetailDao.getLocationById(mId).map {
                LocationDetailDomain(
                    it.id, it.name, it.type, it.dimension, it.residents
                )
            }

        }
    }

    private fun getLocationDetailByRemote(mId: Int) =
        locationDetailApi.getDetailCharacter(mId).map {
            LocationEntity(
                it.id,
                it.name,
                it.type,
                it.dimension,
                it.residents.map { item -> item.split("/").last().toInt() }
            )
        }

}