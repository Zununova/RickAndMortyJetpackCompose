package com.example.retrofitwhithjetpackcompose.data.repositories

import com.example.retrofitwhithjetpackcompose.App
import com.example.retrofitwhithjetpackcompose.data.models.Result
import com.example.retrofitwhithjetpackcompose.presentetion.UiState
import kotlinx.coroutines.flow.flow

class CharacterRepository {

    fun fetchManga() = flow<UiState<List<Result>>> {
        try {
            emit(UiState.Success(App.provideCharacterApiService().fetchCharacters().results))
        } catch (exception: Exception) {
            emit(UiState.Error(exception.message.toString()))
        }
    }
}