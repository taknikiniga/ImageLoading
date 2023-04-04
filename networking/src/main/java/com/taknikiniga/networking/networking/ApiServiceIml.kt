package com.taknikiniga.networking.networking

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

class ApiServiceIml(private val client: HttpClient) : ApiService {


    //    suspend fun getUnsplashPhotos(): List<UnsplashPhoto> {
//        try {
//            // Make a GET request to the Unsplash API with the given client ID
//            val photos = client.get<List<UnsplashPhoto>>("https://api.unsplash.com/photos/") {
//                header("Authorization", "Client-ID $clientId")
//            }
//
//            return photos
//        } catch (e: Exception) {
//            // Handle any exceptions that may occur
//            throw Exception("Error retrieving Unsplash photos: ${e.message}")
//        } finally {
//            // Close the client to release resources
//            client.close()
//        }
//    }
    override suspend fun getUnsplashPhotos(): List<com.google.gson.JsonObject> {

        try {
            // Make a GET request to the Unsplash API with the given client ID
            val photos = client.get<List<com.google.gson.JsonObject>>("https://api.unsplash.com/photos/") {
                header("Authorization", "Client-ID 2A8Q5l-XPEmhYnp_-8seOGkhFQCMy3pssHhRyyL-eWk")
            }

            return photos
        } catch (e: Exception) {
            // Handle any exceptions that may occur
            throw Exception("Error retrieving Unsplash photos: ${e.message}")
        } finally {
            // Close the client to release resources
            client.close()
        }
    }


}

@Serializable
data class UnsplashPhoto(
    val id: String,
    val urls: UnsplashUrls
)

@Serializable
data class UnsplashUrls(
    @SerialName("regular")
    val regularUrl: String
)