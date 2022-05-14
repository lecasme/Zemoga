package com.leo.zemoga.presentation.features.details

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.leo.zemoga.R
import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.domain.models.Result
import com.leo.zemoga.domain.models.User
import com.leo.zemoga.domain.usecases.GetUserUseCase
import com.leo.zemoga.domain.usecases.UpdatePostUseCase
import com.leo.zemoga.presentation.commons.BaseViewModel
import kotlinx.coroutines.launch

class DetailsViewModel(private val updatePostUseCase: UpdatePostUseCase, private val userUseCase: GetUserUseCase) : BaseViewModel(){

    val user = MediatorLiveData<User?>()

    fun updatePost(post: Post) = viewModelScope.launch {
        updatePostUseCase.invoke(post)
    }

    fun getUser(id: Int) = viewModelScope.launch {
        when(val result =  userUseCase.getUser(id)){
            is Result.Success -> {
                user.postValue(result.data)
            }
            is Result.Error -> {}
            is Result.Disconected -> {}
        }
    }

}