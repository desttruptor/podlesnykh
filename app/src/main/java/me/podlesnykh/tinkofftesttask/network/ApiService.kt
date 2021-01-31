package me.podlesnykh.tinkofftesttask.network

import me.podlesnykh.tinkofftesttask.network.pojo.PostsPage
import me.podlesnykh.tinkofftesttask.network.pojo.ResultItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("latest/{page}?json=true")
    suspend fun getLatestPostsByPage(@Path("page") page: Int): Response<PostsPage>

    @GET("hot/{page}?json=true")
    suspend fun getHotPostsByPage(@Path("page") page: Int): Response<PostsPage>

    @GET("top/{page}?json=true")
    suspend fun getTopPostsByPage(@Path("page") page: Int): Response<PostsPage>

    @GET("random?json=true")
    suspend fun getRandomPost(): Response<ResultItem>
}