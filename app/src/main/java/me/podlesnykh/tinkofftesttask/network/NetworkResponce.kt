package me.podlesnykh.tinkofftesttask.network

import me.podlesnykh.tinkofftesttask.presentation.models.Page

sealed class NetworkResponse {
    data class Success(val data: Page)
    data class NetworkError(val error: String)
}
