package com.ken1427.bulletinboardapi.kt.service.user

interface UserService {
    fun get(userId: Int): UserResponse

    fun create(userData: UserRequest): UserResponse

    fun update(userId: Int, userData: UserRequest): UserResponse

    fun delete(userId: Int)
}