package com.example.retrofitwhithjetpackcompose.presentetion.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.retrofitwhithjetpackcompose.data.models.Result
import com.example.retrofitwhithjetpackcompose.presentetion.UiState
import com.example.retrofitwhithjetpackcompose.presentetion.screens.characters.CharactersList
import com.example.retrofitwhithjetpackcompose.presentetion.screens.characters.ProgressBar
import com.example.retrofitwhithjetpackcompose.ui.theme.DarkGray
import com.example.retrofitwhithjetpackcompose.ui.theme.RetrofitWhithJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitWhithJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkGray
                ) {
                    val characterViewModel: CharacterViewModel = hiltViewModel()
                    val characterDataState = characterViewModel.characterData.collectAsState()

                    when (characterDataState.value) {
                        is UiState.Error -> {
                            Log.e("TAG", "onCreate: Error")
                        }

                        is UiState.IsLoading -> {
                            ProgressBar(modifier = Modifier.fillMaxSize())
                        }

                        is UiState.Success -> {
                            CharactersList((characterDataState.value as UiState.Success<List<Result>>).result)
                        }
                    }
                }
            }
        }
    }
}