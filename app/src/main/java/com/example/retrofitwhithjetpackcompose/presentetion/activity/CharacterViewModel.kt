package com.example.retrofitwhithjetpackcompose.presentetion.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitwhithjetpackcompose.data.models.Result
import com.example.retrofitwhithjetpackcompose.data.repositories.CharacterRepository
import com.example.retrofitwhithjetpackcompose.presentetion.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    private var _characterData = MutableStateFlow<UiState<List<Result>>>(UiState.IsLoading())
    val characterData = _characterData.asStateFlow()

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            characterRepository.fetchManga {  }.collect {
                _characterData.value = UiState.Success(it)
            }
        }
    }
}