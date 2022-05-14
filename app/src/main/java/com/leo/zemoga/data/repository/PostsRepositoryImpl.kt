package com.leo.zemoga.data.repository

import androidx.lifecycle.LiveData
import com.leo.zemoga.data.datasource.local.PostLocalDataSource
import com.leo.zemoga.data.datasource.remote.PostRemoteDataSource
import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.domain.repository.PostRepository
import com.leo.zemoga.presentation.utils.toListedPosts

class PostsRepositoryImpl(private val postRemoteDataSource: PostRemoteDataSource,
                          private val postLocalDataSource: PostLocalDataSource
): PostRepository {

    // Cloud
    override suspend fun fetchPosts() {
        val response = postRemoteDataSource.fetchPosts()
        postLocalDataSource.insertPosts(response)
    }

    // Local
    override suspend fun getPosts(favourite: Boolean): LiveData<List<PostEntity>?> {
        return postLocalDataSource.selectPosts(favourite)
    }

    override suspend fun updatePost(post: Post) {
        postLocalDataSource.updatePost(post)
    }

    override suspend fun deletePost(post: PostEntity) {
        postLocalDataSource.deletePost(post)
    }
    override suspend fun deleteAllPosts() {
        postLocalDataSource.deleteAllPosts()
    }
}