package com.example.retrofitwhithjetpackcompose

import android.app.Application
import com.example.retrofitwhithjetpackcompose.data.remote.RetrofitClient
import com.example.retrofitwhithjetpackcompose.data.remote.apiservice.CharacterApiService

class App : Application() {
    companion object {
        private lateinit var retrofitClient: RetrofitClient

        fun provideRetrofitClient(): RetrofitClient {
            return retrofitClient
        }

        fun provideCharacterApiService(): CharacterApiService {
            return retrofitClient.provideCharacterApiService()
        }
    }

    override fun onCreate() {
        super.onCreate()
        initializeRetrofit()
    }

    private fun initializeRetrofit() {
        retrofitClient = RetrofitClient()
    }
}
