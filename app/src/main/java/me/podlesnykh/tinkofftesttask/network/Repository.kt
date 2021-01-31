package me.podlesnykh.tinkofftesttask.network

import me.podlesnykh.tinkofftesttask.network.pojo.PostsPage
import me.podlesnykh.tinkofftesttask.network.pojo.ResultItem
import me.podlesnykh.tinkofftesttask.presentation.models.Category
import me.podlesnykh.tinkofftesttask.presentation.models.Post
import java.io.IOException

class Repository {

    suspend fun getPostsByCategory(page: Int, category: Category): Any {
        return try {
            val response = when (category) {
                Category.LAST -> ApiInstance.api.getLatestPostsByPage(page)
                Category.HOT -> ApiInstance.api.getHotPostsByPage(page)
                Category.POPULAR -> ApiInstance.api.getTopPostsByPage(page)
                else -> return Any()
            }
            if (response.isSuccessful && !response.body()?.result.isNullOrEmpty()) {
                NetworkResponse.Success(
                    mapPost(response.body() ?: PostsPage(emptyList(), 0))
                )
            } else {
                NetworkResponse.NetworkError("Unknown error")
            }
        } catch (error: IOException) {
            NetworkResponse.NetworkError(error.message ?: "Unknown error")
        }
    }

    suspend fun getRandomPost(): Any {
        return try {
            val response = ApiInstance.api.getRandomPost()
            if (response.isSuccessful) {
                NetworkResponse.SuccessRandomPost(
                    mapRandomPost(response.body() ?: ResultItem("", "", 0))
                )
            } else {
                NetworkResponse.NetworkError("Unknown error")
            }
        } catch (error: IOException) {
            NetworkResponse.NetworkError(error.message ?: "Unknown error")
        }
    }

    // transforms nullable response to entity for ui
    private fun mapPost(nullablePage: PostsPage): List<Post> {
        val listOfPosts = nullablePage.result
        return listOfPosts?.map { nullablePost ->
            Post(
                description = nullablePost.description ?: "",
                gifURL = nullablePost.gifURL ?: "",
                id = nullablePost.id ?: 0
            )
        } ?: emptyList()
    }

    private fun mapRandomPost(nullablePost: ResultItem) =
        Post(nullablePost.description ?: "", nullablePost.gifURL ?: "", nullablePost.id ?: 0)
}