package com.example.retrofitwhithjetpackcompose.data.repositories

import com.example.retrofitwhithjetpackcompose.data.remote.apiservice.CharacterApiService
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import javax.inject.Inject
import javax.security.auth.login.LoginException

class CharacterRepository @Inject constructor(private val apiService: CharacterApiService) {

    fun fetchManga(onError: (errorMessage: String) -> Unit) = flow{
        try {
            emit(apiService.fetchCharacters().results)
        } catch (exception: Exception) {
            onError(exception.message.toString())
        } catch (exception: LoginException) {
            onError(exception.message.toString())
        }
    }
}