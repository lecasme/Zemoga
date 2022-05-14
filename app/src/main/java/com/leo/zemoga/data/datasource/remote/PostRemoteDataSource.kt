package com.leo.zemoga.data.datasource.remote

import com.leo.zemoga.data.datasource.remote.service.PostService
import com.leo.zemoga.data.entity.PostEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PostRemoteDataSource {
    suspend fun fetchPosts(): List<PostEntity>
}

class PostRemoteDataSourceImpl(
    private val postService: PostService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PostRemoteDataSource {

    override suspend fun fetchPosts() = withContext(ioDispatcher) {
        postService.fetchPosts()
    }

}