package com.example.rickandmorty.location.data.detail.local

import androidx.room.*
import com.example.rickandmorty.location.data.detail.local.model.LocationEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface LocationDetailDao {

    @Query("SELECT * from locations WHERE id = :idLocation")
    fun getLocationById(idLocation: Int): Observable<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(location: LocationEntity): Completable

}