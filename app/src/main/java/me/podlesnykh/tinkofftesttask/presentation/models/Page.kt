package me.podlesnykh.tinkofftesttask.presentation.models

data class Page(
    val result: List<Post>
)

data class Post(
    val description: String,
    val gifURL: String,
    val id: Int
)
