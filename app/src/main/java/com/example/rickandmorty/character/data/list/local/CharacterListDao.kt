package com.example.rickandmorty.character.data.list.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity
import com.example.rickandmorty.character.data.list.local.model.CharacterForDetailCacheEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CharacterListDao {

    @Query(
        "SELECT * FROM characters WHERE " +
                "(:name = '' OR name LIKE '%' || :name || '%') AND " +
                "(:status = '' OR LOWER(status) = LOWER(:status)) AND " +
                "(:species = '' OR LOWER(species)  LIKE '%' || LOWER(:species) || '%') AND " +
                "(:gender = '' OR LOWER(gender) = LOWER(:gender))"
    )
    fun getPagingCharacter(
        name: String,
        status: String,
        species: String,
        gender: String,
    ): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(characters: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCharacter(characters: CharacterEntity): Completable


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCache(characters: List<CharacterForDetailCacheEntity>)

    @Query("SELECT * FROM characters_cache WHERE id IN (:characterListFilter)")
    fun getPagingCharacterCache(
        characterListFilter: List<Int>,
    ): PagingSource<Int, CharacterForDetailCacheEntity>


    @Query("SELECT * from characters WHERE id = :idCharacter")
    fun getCharacterById(idCharacter: Int): Single<CharacterEntity>
}