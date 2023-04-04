package com.taknikiniga.imageloading

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.taknikiniga.networking.networking.repo.NetworkingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo : NetworkingRepo) : ViewModel() {

    suspend fun getUnsplash() = repo.getDashboard()

}