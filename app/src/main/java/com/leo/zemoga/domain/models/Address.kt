package com.leo.zemoga.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Address(
           var street: String,
           var suite: String,
           var city: String,
           var zipcode: String
) : Parcelable
