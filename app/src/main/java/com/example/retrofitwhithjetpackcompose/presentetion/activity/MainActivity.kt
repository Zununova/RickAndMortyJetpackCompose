package com.example.retrofitwhithjetpackcompose.presentetion.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.retrofitwhithjetpackcompose.presentetion.screens.characters.MangaList
import com.example.retrofitwhithjetpackcompose.presentetion.screens.characters.ProgressBar
import com.example.retrofitwhithjetpackcompose.ui.theme.DarkGray
import com.example.retrofitwhithjetpackcompose.ui.theme.RetrofitWhithJetpackComposeTheme

class MainActivity : ComponentActivity() {
    private val characterViewModel = CharacterViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitWhithJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkGray
                ) {
                    if (characterViewModel.showProgressBar.collectAsState().value) {
                        Log.e("TAG", "onCreate: progressBar", )
                        ProgressBar()
                    } else {
                        MangaList(viewModel = CharacterViewModel())
                    }
                }
            }
        }
    }
}