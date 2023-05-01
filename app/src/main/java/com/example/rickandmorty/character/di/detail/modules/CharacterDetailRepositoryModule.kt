package com.example.rickandmorty.character.di.detail.modules

import com.example.rickandmorty.character.data.detail.CharacterDetailRepositoryImpl
import com.example.rickandmorty.character.data.detail.remote.CharacterDetailApi
import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CharacterDetailRepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterDetailRepositoryImpl(characterDetailApi: CharacterDetailApi): CharacterDetailRepositoryImpl {
        return CharacterDetailRepositoryImpl(
            characterDetailApi
        )
    }

    @Singleton
    @Provides
    fun provideCharacterDetailRepository(characterDetailRepositoryImpl: CharacterDetailRepositoryImpl): CharacterDetailRepository {
        return characterDetailRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideCharacterDetailApi(retrofit: Retrofit): CharacterDetailApi {
        return retrofit.create(CharacterDetailApi::class.java)
    }
}