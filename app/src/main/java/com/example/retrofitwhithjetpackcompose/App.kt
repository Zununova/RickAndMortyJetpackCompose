package com.example.retrofitwhithjetpackcompose

import android.app.Application
import com.example.retrofitwhithjetpackcompose.data.remote.RetrofitClient
import com.example.retrofitwhithjetpackcompose.data.remote.apiservice.CharacterApiService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()