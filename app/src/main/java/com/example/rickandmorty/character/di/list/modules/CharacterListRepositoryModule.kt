package com.example.rickandmorty.character.di.list.modules

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.character.data.list.CharacterListRepositoryImpl
import com.example.rickandmorty.character.data.list.local.CharacterDatabase
import com.example.rickandmorty.character.data.list.local.CharactersListDao
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

    @Singleton
    @Provides
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
    fun provideCharacterDao(database: CharacterDatabase): CharactersListDao {
        return database.characterDao()
    }


    @Singleton
    @Provides
    fun provideCharacterListRepositoryImpl(characterListApi: CharacterListApi, charactersListDao: CharactersListDao): CharacterListRepositoryImpl {
        return CharacterListRepositoryImpl(
            characterListApi,
            charactersListDao
        )
    }

}