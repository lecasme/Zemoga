package com.leo.zemoga.presentation.features.home

import androidx.lifecycle.viewModelScope
import com.leo.zemoga.R
import com.leo.zemoga.domain.models.Result
import com.leo.zemoga.domain.usecases.GetPostUseCase
import kotlinx.coroutines.launch
import com.leo.zemoga.domain.usecases.RemoveAllPostUseCase
import com.leo.zemoga.presentation.commons.BaseViewModel
import com.leo.zemoga.presentation.utils.Event


class HomeViewModel(private val removeAllPostUseCase: RemoveAllPostUseCase,
                    private val getPostUseCase: GetPostUseCase) : BaseViewModel() {

    fun removeAllPosts() = viewModelScope.launch {
        removeAllPostUseCase.invoke()
    }

    fun fetchPosts() = viewModelScope.launch {
        when(getPostUseCase.fetchPosts()){
            is Result.Success -> { }
            is Result.Error -> _snackBar.value = Event(R.string.error)
            is Result.Disconected -> {
                _snackBar.value = Event(R.string.connectivity)
            }
        }
    }

}