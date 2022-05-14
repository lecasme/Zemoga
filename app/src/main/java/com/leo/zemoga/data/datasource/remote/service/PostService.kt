package com.leo.zemoga.data.datasource.remote.service

import com.leo.zemoga.data.entity.PostEntity
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    suspend fun fetchPosts(): List<PostEntity>

}