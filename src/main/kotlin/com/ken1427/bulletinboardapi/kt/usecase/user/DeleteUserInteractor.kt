package com.ken1427.bulletinboardapi.kt.usecase.user

import com.ken1427.bulletinboardapi.kt.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class DeleteUserInteractor(
    private val userRepository: UserRepository
): DeleteUserUseCase {
    override fun handle(userId: Int) {
        TODO("Not yet implemented")
    }
}