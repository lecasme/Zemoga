package com.leo.zemoga.domain.repository

import com.leo.zemoga.domain.models.User

interface UserRepository {

    // Cloud
    suspend fun fetchUser(id: Int): User

}