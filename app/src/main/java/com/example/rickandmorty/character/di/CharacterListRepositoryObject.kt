package com.example.rickandmorty.character.di

import com.example.rickandmorty.character.data.list.CharacterListRepositoryImpl
import com.example.rickandmorty.character.data.list.remote.CharacterListApi
import kotlinx.coroutines.Dispatchers

object CharacterListRepositoryObject {

    private val usersRepository = CharacterListRepositoryImpl(
        Dispatchers.IO,
        RetrofitClient.getClient().create(CharacterListApi::class.java)
    )

    fun getCharacterRepository() = usersRepository
}