package com.example.rickandmorty.character.data.list.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmorty.character.data.list.local.model.CharacterEntity

@Dao
interface CharactersListDao {

    @Query(
        "SELECT * FROM characters WHERE " +
                "(:name = '' OR name LIKE '%' || :name || '%') AND " +
                "(:status = '' OR LOWER(status) = LOWER(:status)) AND " +
                "(:species = '' OR LOWER(species)  LIKE '%' || LOWER(:species) || '%') AND " +
                "(:gender = '' OR gender = :gender)"
    )
    fun getPagingCharacter(
        name: String,
        status: String,
        species: String,
        gender: String
    ): PagingSource<Int, CharacterEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(characters: List<CharacterEntity>)

    @Query(
        "DELETE FROM characters WHERE " +
                "(:name = '' OR name LIKE '%' || :name || '%') AND " +
                "(:status = '' OR LOWER(status) = LOWER(:status)) AND " +
                "(:species = '' OR LOWER(species)  LIKE '%' || LOWER(:species) || '%') AND " +
                "(:gender = '' OR gender = :gender)"
    )
    suspend fun clear(
        name: String,
        status: String,
        species: String,
        gender: String
    )

    @Transaction
    suspend fun refresh(
        characters: List<CharacterEntity>,
        name: String,
        status: String,
        species: String,
        gender: String
    ) {
        clear(name, status, species, gender )
        save(characters)
    }
}