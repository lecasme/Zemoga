package com.leo.zemoga.domain.usecases

import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.domain.models.Result
import java.lang.Exception
import com.leo.zemoga.domain.repository.PostRepository
import com.leo.zemoga.presentation.utils.Connectivity

class RemoveAllPostUseCase(private val postRepository: PostRepository) {
    suspend fun invoke() { return postRepository.deleteAllPosts() }
}