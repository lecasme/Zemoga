package com.leo.zemoga.domain.usecases

import androidx.lifecycle.LiveData
import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.domain.models.Result
import java.lang.Exception
import com.leo.zemoga.domain.repository.PostRepository
import com.leo.zemoga.presentation.utils.Connectivity

class GetPostUseCase(
    private val postRepository: PostRepository,
    private val connectivity: Connectivity
) {

    suspend fun fetchPosts(): Result<Boolean>{
        return if(connectivity.isOnline()){
            try {
                postRepository.fetchPosts()
                Result.Success(true)

            }catch (e: Exception){
                Result.Error(e)
            }
        }else{
            Result.Disconected
        }
    }

    suspend fun getPosts(favourite: Boolean): LiveData<List<PostEntity>?> {
        return postRepository.getPosts(favourite)
    }

}