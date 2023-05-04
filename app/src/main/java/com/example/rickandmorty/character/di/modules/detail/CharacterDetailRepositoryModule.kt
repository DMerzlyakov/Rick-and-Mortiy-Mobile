package com.example.rickandmorty.character.di.modules.detail

import com.example.rickandmorty.character.data.detail.CharacterDetailRepositoryImpl
import com.example.rickandmorty.character.data.detail.mapper.CharacterDetailDtoToCharacterEntityMapper
import com.example.rickandmorty.character.data.detail.mapper.CharacterEntityToCharacterDetailDomainMapper
import com.example.rickandmorty.character.data.detail.remote.CharacterDetailApi
import com.example.rickandmorty.character.data.list.local.CharacterListDao
import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CharacterDetailRepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterDetailRepositoryImpl(
        characterDetailApi: CharacterDetailApi,
        characterListDao: CharacterListDao,
        dtoToEntityMapper: CharacterDetailDtoToCharacterEntityMapper,
        entityTpDomainMapper: CharacterEntityToCharacterDetailDomainMapper
    ): CharacterDetailRepositoryImpl {
        return CharacterDetailRepositoryImpl(
            characterDetailApi,
            characterListDao,
            dtoToEntityMapper,
            entityTpDomainMapper
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