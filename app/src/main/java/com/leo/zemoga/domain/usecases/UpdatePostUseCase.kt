package com.leo.zemoga.domain.usecases

import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.domain.repository.PostRepository

class UpdatePostUseCase(private val postRepository: PostRepository) {
    suspend fun invoke(post: Post) { return postRepository.updatePost(post) }
}