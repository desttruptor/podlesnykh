package me.podlesnykh.tinkofftesttask.network

import me.podlesnykh.tinkofftesttask.presentation.models.Post

sealed class NetworkResponse {
    data class Success(val data: List<Post>)
    data class SuccessRandomPost(val data: Post)
    data class NetworkError(val error: String)
}
