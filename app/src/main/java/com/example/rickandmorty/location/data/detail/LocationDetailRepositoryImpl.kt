package com.example.rickandmorty.location.data.detail

import com.example.rickandmorty.location.data.detail.mapper.LocationDetailDtoToLocationEntityMapper
import com.example.rickandmorty.location.data.detail.mapper.LocationEntityToLocationDetailDomainMapper
import com.example.rickandmorty.location.data.detail.remote.LocationDetailApi
import com.example.rickandmorty.location.data.list.local.LocationDao
import com.example.rickandmorty.location.domain.detail.LocationDetailRepository
import com.example.rickandmorty.location.domain.detail.model.LocationDetailDomain
import io.reactivex.Single
import javax.inject.Inject

class LocationDetailRepositoryImpl @Inject constructor(
    private val locationDetailApi: LocationDetailApi,
    private val locationDetailDao: LocationDao,
    private val dtoToEntityMapper: LocationDetailDtoToLocationEntityMapper,
    private val entityToDomainMapper: LocationEntityToLocationDetailDomainMapper
) : LocationDetailRepository {
    override fun getLocationDetail(mId: Int): Single<LocationDetailDomain> {

        return getLocationDetailByRemote(mId)
            .flatMap {
                locationDetailDao.saveLocation(it).andThen(Single.just(entityToDomainMapper(it)))
            }.onErrorResumeNext { _: Throwable ->
                locationDetailDao.getLocationById(mId).map {
                    entityToDomainMapper(it)
                }
            }
    }

    private fun getLocationDetailByRemote(mId: Int) =
        locationDetailApi.getDetailCharacter(mId).map {
            dtoToEntityMapper(it)
        }

}