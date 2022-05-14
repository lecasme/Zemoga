package com.leo.zemoga.presentation.features.home.fragment

import androidx.lifecycle.*
import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.domain.usecases.GetPostUseCase
import com.leo.zemoga.domain.usecases.RemovePostUseCase
import com.leo.zemoga.presentation.commons.BaseViewModel
import kotlinx.coroutines.launch

class PageViewModel(private val getPostUseCase: GetPostUseCase,
                    private val deletePostUseCase: RemovePostUseCase) : BaseViewModel() {

    lateinit var posts : LiveData<List<PostEntity>?>

    fun getPosts(favourite: Boolean) = viewModelScope.launch {
        posts = getPostUseCase.getPosts(favourite)
    }

    fun deletePost(post: PostEntity) = viewModelScope.launch {
        deletePostUseCase.invoke(post)
    }

}