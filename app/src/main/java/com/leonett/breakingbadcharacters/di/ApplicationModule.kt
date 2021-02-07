package com.leonett.breakingbadcharacters.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.leonett.breakingbadcharacters.data.local.db.CharactersDatabase
import com.leonett.breakingbadcharacters.data.remote.CharactersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://www.breakingbadapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCharactersApiService(retrofit: Retrofit): CharactersApiService {
        return retrofit.create(CharactersApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDataBase(application: Application): CharactersDatabase {
        return Room.databaseBuilder(application, CharactersDatabase::class.java, "Characters.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    }

}