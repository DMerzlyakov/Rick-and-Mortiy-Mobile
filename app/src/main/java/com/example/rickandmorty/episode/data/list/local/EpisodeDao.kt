package com.example.rickandmorty.episode.data.list.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity

@Dao
interface EpisodeDao {

    @Query(
        "SELECT * FROM episodes WHERE " +
                "(:name = '' OR LOWER(name) LIKE '%' || LOWER(:name) || '%') AND " +
                "(:episode = '' OR LOWER(episode) LIKE '%' || LOWER(:episode) || '%')"
    )
    fun getPagingEpisode(
        name: String,
        episode: String,
    ): PagingSource<Int, EpisodeEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(episodes: List<EpisodeEntity>)

    @Query(
        "DELETE FROM episodes WHERE " +
                "(:name = '' OR LOWER(name) LIKE '%' || LOWER(:name) || '%') AND " +
                "(:episode = '' OR LOWER(episode) LIKE '%' || LOWER(:episode) || '%')"
    )
    suspend fun clear(
        name: String,
        episode: String,
    )

    @Transaction
    suspend fun refresh(
        episodes: List<EpisodeEntity>,
        name: String, episode: String,
    ) {
        clear(name, episode)
        save(episodes)
    }
}