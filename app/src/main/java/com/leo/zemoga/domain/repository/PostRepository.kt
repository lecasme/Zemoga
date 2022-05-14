package com.leo.zemoga.domain.repository

import androidx.lifecycle.LiveData
import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.domain.models.Post


interface PostRepository {

    // Cloud
    suspend fun fetchPosts()

    // Local
    suspend fun getPosts(favourite: Boolean): LiveData<List<PostEntity>?>
    suspend fun updatePost(post: Post)
    suspend fun deletePost(post: PostEntity)
    suspend fun deleteAllPosts()


}