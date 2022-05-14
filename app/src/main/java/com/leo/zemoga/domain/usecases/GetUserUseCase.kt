package com.leo.zemoga.domain.usecases

import com.leo.zemoga.domain.models.Result
import com.leo.zemoga.domain.models.User
import java.lang.Exception
import com.leo.zemoga.domain.repository.UserRepository
import com.leo.zemoga.presentation.utils.Connectivity

class GetUserUseCase(
    private val userRepository: UserRepository,
    private val connectivity: Connectivity
) {

    suspend fun getUser(id: Int): Result<User>{
        return if(connectivity.isOnline()){
            try {
                Result.Success(userRepository.fetchUser(id))
            }catch (e: Exception){
                Result.Error(e)
            }
        }else{
            Result.Disconected
        }
    }

}