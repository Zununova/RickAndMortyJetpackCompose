package com.example.retrofitwhithjetpackcompose.module

import com.example.retrofitwhithjetpackcompose.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Singleton
    var retrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun provideCharacterApiService() = retrofitClient.provideCharacterApiService()
}