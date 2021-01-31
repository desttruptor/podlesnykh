package me.podlesnykh.tinkofftesttask.network

import me.podlesnykh.tinkofftesttask.network.pojo.PostsPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("latest/{page}?json=true")
    suspend fun getLatestPostsByPage(@Path("page") page: Int): Call<PostsPage>

    @GET("hot/{page}?json=true")
    suspend fun getHotPostsByPage(@Path("page") page: Int): Call<PostsPage>

    @GET("top/{page}?json=true")
    suspend fun getTopPostsByPage(@Path("page") page: Int): Call<PostsPage>
}