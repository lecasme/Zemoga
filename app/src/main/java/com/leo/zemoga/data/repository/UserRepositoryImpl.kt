package com.leo.zemoga.data.repository

import com.leo.zemoga.data.datasource.remote.UserRemoteDataSource
import com.leo.zemoga.domain.models.User
import com.leo.zemoga.domain.repository.UserRepository


class UserRepositoryImpl(private val userRemoteDataSource: UserRemoteDataSource): UserRepository {

    // Cloud
    override suspend fun fetchUser(id: Int): User {
        return userRemoteDataSource.getUser(id)
    }

}