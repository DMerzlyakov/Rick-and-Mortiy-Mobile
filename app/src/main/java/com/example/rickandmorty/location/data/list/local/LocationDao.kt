package com.example.rickandmorty.location.data.list.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.location.data.list.local.model.LocationEntity

@Dao
interface LocationDao {

    @Query(
        "SELECT * FROM locations WHERE " +
                "(:name = '' OR name LIKE '%' || :name || '%') AND " +
                "(:type = '' OR type = :type) AND " +
                "(:dimension = '' OR dimension = :dimension)"
    )
    fun getPagingLocation(
        name: String,
        type: String,
        dimension: String
    ): PagingSource<Int, LocationEntity>
//name: String, type: String,dimension: String


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(locations: List<LocationEntity>)

    @Query(
        "DELETE FROM locations WHERE " +
                "(:name = '' OR name LIKE '%' || :name || '%') AND " +
                "(:type = '' OR type = :type) AND " +
                "(:dimension = '' OR dimension = :dimension)"
    )
    suspend fun clear(
        name: String,
        type: String,
        dimension: String
    )

    @Transaction
    suspend fun refresh(
        locations: List<LocationEntity>, name: String,
        type: String,
        dimension: String
    ) {
        clear(name, type, dimension)
        save(locations)
    }
}