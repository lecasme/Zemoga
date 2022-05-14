package com.leo.zemoga.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Post(var userId: Int,
           var id: Int,
           var title: String,
           var body: String,
           var favourite: Boolean) : Parcelable


