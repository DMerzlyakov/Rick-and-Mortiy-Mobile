package com.example.rickandmorty.character.di

import com.example.rickandmorty.character.data.detail.CharacterDetailRepositoryImpl
import com.example.rickandmorty.character.data.detail.remote.CharacterDetailApi
import com.example.rickandmorty.character.data.list.CharacterListRepositoryImpl
import com.example.rickandmorty.character.data.list.remote.CharacterListApi
import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository
import kotlinx.coroutines.Dispatchers

object CharacterDetailRepositoryObject {

    private val usersRepository = CharacterDetailRepositoryImpl(
        RetrofitClient.getClient().create(CharacterDetailApi::class.java)
    )

    fun getCharacterRepository() = usersRepository
}