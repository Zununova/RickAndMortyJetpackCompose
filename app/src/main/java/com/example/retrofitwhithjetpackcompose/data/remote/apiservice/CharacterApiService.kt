package com.example.retrofitwhithjetpackcompose.data.remote.apiservice

import com.example.retrofitwhithjetpackcompose.data.models.CharacterResponse
import retrofit2.http.GET

interface CharacterApiService {

    @GET("character")
    suspend fun fetchCharacters(): CharacterResponse
}