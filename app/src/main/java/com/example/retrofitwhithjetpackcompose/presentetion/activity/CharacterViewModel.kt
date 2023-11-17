package com.example.retrofitwhithjetpackcompose.presentetion.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitwhithjetpackcompose.data.models.Result
import com.example.retrofitwhithjetpackcompose.data.repositories.CharacterRepository
import com.example.retrofitwhithjetpackcompose.presentetion.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private var _characterData = MutableStateFlow<List<Result>>(emptyList())
    val characterData = _characterData.asStateFlow()
    private val characterRepository = CharacterRepository()
    private var _showProgressBar = MutableStateFlow<Boolean>(true)
    val showProgressBar = _showProgressBar.asStateFlow()

    init {
        fetchManga()
    }

    private fun fetchManga() {
        viewModelScope.launch {
            characterRepository.fetchManga().collect {
                when (it) {
                    is UiState.Error -> {
                        _showProgressBar.value = false
                    }
                    is UiState.IsLoading -> {
                        _showProgressBar.value = true
                    }
                    is UiState.Success -> {
                        _showProgressBar.value = false
                        _characterData.value = it.result
                    }
                }
            }
        }
    }
}