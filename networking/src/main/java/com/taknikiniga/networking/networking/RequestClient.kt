package com.taknikiniga.networking.networking

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

object RequestClient {

    fun getInstance(): ApiService {
        return ApiServiceIml(
            client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = GsonSerializer(){
                        setPrettyPrinting()
                        disableHtmlEscaping()
                    }
                }
                install(HttpTimeout) {
                    socketTimeoutMillis = 15000L
                    connectTimeoutMillis = 15000L
                    connectTimeoutMillis = 15000L
                }

                defaultRequest {
                    if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
            }
        )
    }

    val json = kotlinx.serialization.json.Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
        encodeDefaults = false
    }
}