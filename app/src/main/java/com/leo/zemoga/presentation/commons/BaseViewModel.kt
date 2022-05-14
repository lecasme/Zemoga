package com.leo.zemoga.presentation.commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leo.zemoga.presentation.utils.Event

abstract class BaseViewModel : ViewModel() {

    protected val _snackBar = MutableLiveData<Event<Int>>()
    val snackBar: LiveData<Event<Int>> get() = _snackBar

}