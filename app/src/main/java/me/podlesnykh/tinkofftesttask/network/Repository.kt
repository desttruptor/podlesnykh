package me.podlesnykh.tinkofftesttask.network

import me.podlesnykh.tinkofftesttask.network.pojo.PostsPage
import me.podlesnykh.tinkofftesttask.presentation.models.Categories
import me.podlesnykh.tinkofftesttask.presentation.models.ModelResultItem
import me.podlesnykh.tinkofftesttask.presentation.models.Page
import retrofit2.awaitResponse
import java.io.IOException

class Repository {
    suspend fun getLatestPostsByPage(page: Int) =
        try {
            val response = ApiInstance.api.getLatestPostsByPage(page).awaitResponse()
            if (response.isSuccessful) {
                NetworkResponse.Success(
                    mapper(response.body() ?: PostsPage(emptyList(), 0))
                )
            } else {
                NetworkResponse.NetworkError("Unknown error")
            }
        } catch (error: IOException) {
            NetworkResponse.NetworkError(error.message ?: "Unknown error")
        }

    suspend fun getHotPostsByPage(page: Int) =
        try {
            val response = ApiInstance.api.getHotPostsByPage(page).awaitResponse()
            if (response.isSuccessful) {
                NetworkResponse.Success(
                    mapper(response.body() ?: PostsPage(emptyList(), 0))
                )
            } else {
                NetworkResponse.NetworkError("Unknown error")
            }
        } catch (error: IOException) {
            NetworkResponse.NetworkError(error.message ?: "Unknown error")
        }

    suspend fun getTopPostsByPage(page: Int) =
        try {
            val response = ApiInstance.api.getTopPostsByPage(page).awaitResponse()
            if (response.isSuccessful) {
                NetworkResponse.Success(
                    mapper(response.body() ?: PostsPage(emptyList(), 0))
                )
            } else {
                NetworkResponse.NetworkError("Unknown error")
            }
        } catch (error: IOException) {
            NetworkResponse.NetworkError(error.message ?: "Unknown error")
        }

    // transforms nullable response to entity for ui
    private fun mapper(nullablePage: PostsPage): Page {
        val listOfPosts = nullablePage.result
        return Page(listOfPosts?.map { nullablePost ->
            ModelResultItem(
                description = nullablePost.description ?: "",
                gifURL = nullablePost.gifURL ?: "",
                id = nullablePost.id ?: 0
            )
        } ?: emptyList())
    }
}