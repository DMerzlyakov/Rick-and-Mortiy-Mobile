package com.example.rickandmorty.episode.data.list.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.data.list.local.model.CharacterForDetailCacheEntity
import com.example.rickandmorty.episode.data.list.local.model.EpisodeEntity
import com.example.rickandmorty.episode.data.list.local.model.EpisodeForDetailCacheEntity
import io.reactivex.Observable
import io.reactivex.Single

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


    @Query("SELECT * from episodes WHERE id = :idEpisode")
    fun getEpisodeById(idEpisode: Int): Single<EpisodeEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCache(episodes: List<EpisodeForDetailCacheEntity>)

    @Query("SELECT * FROM episodes_cache WHERE id IN (:episodeListFilter)")
    fun getPagingEpisodeCache(
        episodeListFilter: List<Int>,
    ): PagingSource<Int, EpisodeForDetailCacheEntity>



}