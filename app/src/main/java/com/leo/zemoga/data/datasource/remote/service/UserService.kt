package com.leo.zemoga.data.datasource.remote.service

import com.leo.zemoga.domain.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): User

}