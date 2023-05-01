package com.example.rickandmorty.character.di.list

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.character.data.detail.CharacterDetailRepositoryImpl
import com.example.rickandmorty.character.data.detail.remote.CharacterDetailApi
import com.example.rickandmorty.character.data.list.CharacterListRepositoryImpl
import com.example.rickandmorty.character.data.list.local.CharactersListDao
import com.example.rickandmorty.character.data.list.local.CharacterDatabase
import com.example.rickandmorty.character.data.list.remote.CharacterListApi
import com.example.rickandmorty.character.domain.detail.CharacterDetailRepository
import com.example.rickandmorty.character.domain.list.CharacterListRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CharacterListRepositoryModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterListRepository(characterListRepositoryImpl: CharacterListRepositoryImpl): CharacterListRepository {
        return characterListRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "db"
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

//    --------------------------------------- DETAIL

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