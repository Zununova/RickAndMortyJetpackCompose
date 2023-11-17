package com.example.retrofitwhithjetpackcompose.presentetion

import com.example.retrofitwhithjetpackcompose.data.models.Result

sealed class UiState<T> {
    class IsLoading<T>() : UiState<T>()
    class Error<T>(val message: String) : UiState<T>()
    class Success<T>(val result: List<Result>) : UiState<T>()
}