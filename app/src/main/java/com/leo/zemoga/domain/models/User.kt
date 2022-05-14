package com.leo.zemoga.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(var id: Int,
           var name: String,
           var username: String,
           var email: String,
           var address: Address,
           var phone: String,
           var website: String
) : Parcelable