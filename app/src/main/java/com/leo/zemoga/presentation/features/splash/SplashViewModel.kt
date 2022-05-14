package com.leo.zemoga.presentation.features.splash

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.leo.zemoga.R
import kotlinx.coroutines.launch
import com.leo.zemoga.domain.usecases.GetPostUseCase
import com.leo.zemoga.presentation.commons.BaseViewModel
import com.leo.zemoga.domain.models.Result
import com.leo.zemoga.presentation.utils.Event

class SplashViewModel(private val getPostUseCase: GetPostUseCase) : BaseViewModel() {

    val status = MediatorLiveData<Boolean>()

    init {
        fetchPosts()
    }

    fun fetchPosts() = viewModelScope.launch {
        when(getPostUseCase.fetchPosts()){
            is Result.Success -> {
                status.postValue(true)
            }
            is Result.Error -> _snackBar.value = Event(R.string.error)
            is Result.Disconected -> _snackBar.value = Event(R.string.connectivity)
        }
    }

}