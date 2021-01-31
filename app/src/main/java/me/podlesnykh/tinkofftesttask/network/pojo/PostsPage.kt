package me.podlesnykh.tinkofftesttask.network.pojo

import com.google.gson.annotations.SerializedName

data class PostsPage(

	@field:SerializedName("result")
	val result: List<ResultItem>? = null,

	@field:SerializedName("totalCount")
	val totalCount: Int? = null
)

data class ResultItem(

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("gifURL")
	val gifURL: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
