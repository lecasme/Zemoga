package com.leo.zemoga.presentation.utils

import android.app.Activity
import android.view.View
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.domain.models.Post

fun View.setOnSafeClickListener(onSafeClick: (View) -> Unit) {
    setOnClickListener(SafeClickListener { v ->
        onSafeClick(v)
    })
}

fun Window.getSoftInputMode(): Int {
    return attributes.softInputMode
}

fun Activity.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(findViewById(android.R.id.content), snackbarText, timeLength).show()
}

fun Activity.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Event<Int>>,
    timeLength: Int
) {
    snackbarEvent.observe(lifecycleOwner) { event ->
        event.getContentIfNotHandled()?.let { res ->
            showSnackbar(getString(res), timeLength)
        }
    }
}

fun Fragment.showSnackbar(snackbarText: String, timeLength: Int) {
    activity?.let {
        Snackbar.make(it.findViewById(android.R.id.content), snackbarText, timeLength).show()
    }
}

fun Fragment.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Event<Int>>,
    timeLength: Int
) {
    snackbarEvent.observe(lifecycleOwner) { event ->
        event.getContentIfNotHandled()?.let { res ->
            context?.let { showSnackbar(it.getString(res), timeLength) }
        }
    }
}