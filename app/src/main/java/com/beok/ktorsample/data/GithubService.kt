package com.beok.ktorsample.data

import android.util.Log
import com.beok.ktorsample.data.model.RepositoryResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class GithubService(engine: HttpClientEngine = CIO.create()) {
    private val client = HttpClient(engine) {

        install(JsonFeature) {
            serializer = KotlinxSerializer(json = Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
                encodeDefaults = true
            })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("kkk", message)
                }
            }
            level = LogLevel.ALL
        }
    }

    suspend fun fetchRepositoryList(user: String): List<RepositoryResponse> {
        return client.get(urlString = BASE_URL + "users/${user}/repos")
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
}
