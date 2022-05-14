package com.leo.zemoga.presentation.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.leo.zemoga.presentation.utils.getSoftInputMode
import com.leo.zemoga.presentation.utils.setupSnackbar

abstract class BaseActivity: AppCompatActivity() {

    private var originalMode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        originalMode = window?.getSoftInputMode()
        setupSnackbar(this, getViewModel().snackBar, Snackbar.LENGTH_LONG)
    }

    override fun onDestroy() {
        super.onDestroy()
        originalMode?.let { window?.setSoftInputMode(it) }
    }

    abstract fun getViewModel(): BaseViewModel

}