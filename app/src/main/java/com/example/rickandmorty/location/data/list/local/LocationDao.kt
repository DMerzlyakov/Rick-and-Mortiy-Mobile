package com.example.rickandmorty.location.data.list.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.location.data.list.local.model.LocationEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface LocationDao {

    @Query(
        "SELECT * FROM locations WHERE " +
                "(:name = '' OR LOWER(name) LIKE '%' || LOWER(:name) || '%') AND " +
                "(:type = '' OR LOWER(type) LIKE '%' || LOWER(:type) || '%') AND " +
                "(:dimension = '' OR LOWER(dimension) LIKE '%' || LOWER(:dimension) || '%')"
    )
    fun getPagingLocation(
        name: String,
        type: String,
        dimension: String
    ): PagingSource<Int, LocationEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(locations: List<LocationEntity>)


    @Query("SELECT * from locations WHERE id = :idLocation")
    fun getLocationById(idLocation: Int): Single<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLocation(location: LocationEntity): Completable

}