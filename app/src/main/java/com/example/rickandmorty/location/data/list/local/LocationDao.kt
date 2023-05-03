package com.example.rickandmorty.location.data.list.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.location.data.detail.local.model.LocationEntity

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

}