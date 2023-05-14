package com.example.rickandmorty.character.di.modules.list

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.character.data.list.CharacterListRepositoryImpl
import com.example.rickandmorty.character.data.list.local.CharacterDatabase
import com.example.rickandmorty.character.data.list.local.CharacterListDao
import com.example.rickandmorty.character.data.list.mapper.CharacterCacheEntityToCharacterDomainMapper
import com.example.rickandmorty.character.data.list.mapper.CharacterEntityToCharacterDomainMapper
import com.example.rickandmorty.character.data.list.mapper.CharacterPageDtoToCharacterEntityMapper
import com.example.rickandmorty.character.data.list.mapper.CharacterResultDtoToCharacterCacheEntityMapper
import com.example.rickandmorty.character.data.list.remote.CharacterListApi
import com.example.rickandmorty.character.domain.list.CharacterListRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CharacterListRepositoryModule {


    @Singleton
    @Provides
    fun provideCharacterListRepository(characterListRepositoryImpl: CharacterListRepositoryImpl): CharacterListRepository {
        return characterListRepositoryImpl
    }


    @Provides
    @Singleton
    fun provideCharacterDatabase(context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "character"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterListApi(retrofit: Retrofit): CharacterListApi {
        return retrofit.create(CharacterListApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterDao(database: CharacterDatabase): CharacterListDao {
        return database.characterDao()
    }


    @Singleton
    @Provides
    fun provideCharacterListRepositoryImpl(
        characterListApi: CharacterListApi,
        characterListDao: CharacterListDao,
        entityToDomainPagingMapper: CharacterEntityToCharacterDomainMapper,
        cacheEntityToDomainPagingMapper: CharacterCacheEntityToCharacterDomainMapper,
        dtoToCharacterEntityMapper: CharacterPageDtoToCharacterEntityMapper,
        dtoToCharacterCacheEntityMapper: CharacterResultDtoToCharacterCacheEntityMapper
    ): CharacterListRepositoryImpl {
        return CharacterListRepositoryImpl(
            characterListApi,
            characterListDao,
            entityToDomainPagingMapper,
            cacheEntityToDomainPagingMapper,
            dtoToCharacterEntityMapper,
            dtoToCharacterCacheEntityMapper
        )
    }

}