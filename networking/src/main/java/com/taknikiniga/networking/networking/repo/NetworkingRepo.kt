package com.taknikiniga.networking.networking.repo

import com.taknikiniga.networking.networking.RequestClient
import javax.inject.Inject

class NetworkingRepo @Inject constructor() {

    suspend fun getDashboard() = RequestClient.getInstance().getUnsplashPhotos()

}