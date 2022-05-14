package com.leo.zemoga.domain.usecases

import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.domain.repository.PostRepository

class RemovePostUseCase(private val postRepository: PostRepository) {
    suspend fun invoke(post: PostEntity) { return postRepository.deletePost(post) }
}