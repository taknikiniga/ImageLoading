package com.taknikiniga.networking.networking

import kotlinx.serialization.json.JsonObject

interface ApiService {

    suspend fun getUnsplashPhotos(): List<com.google.gson.JsonObject>
}