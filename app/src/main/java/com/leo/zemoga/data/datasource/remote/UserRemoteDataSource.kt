package com.leo.zemoga.data.datasource.remote

import com.leo.zemoga.data.datasource.remote.service.PostService
import com.leo.zemoga.data.datasource.remote.service.UserService
import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.domain.models.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface UserRemoteDataSource {
    suspend fun getUser(id: Int): User
}

class UserRemoteDataSourceImpl(
    private val userService: UserService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRemoteDataSource {

    override suspend fun getUser(id: Int) = withContext(ioDispatcher) {
        userService.getUser(id)
    }

}