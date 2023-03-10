package com.ken1427.bulletinboardapi.kt.usecase.user

import com.ken1427.bulletinboardapi.kt.repository.UserRepository
import org.springframework.stereotype.Service

interface DeleteUserUseCase {
    fun handle(userId: Int)
}

@Service
class DeleteUserUseCaseImpl(
    private val userRepository: UserRepository
): DeleteUserUseCase {
    override fun handle(userId: Int) {
        userRepository.deleteUser(userId)
    }
}