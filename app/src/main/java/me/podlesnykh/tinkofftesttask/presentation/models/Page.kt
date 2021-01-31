package me.podlesnykh.tinkofftesttask.presentation.models

data class Page(
    val result: List<ModelResultItem>
)

data class ModelResultItem(
    val description: String,
    val gifURL: String,
    val id: Int
)
