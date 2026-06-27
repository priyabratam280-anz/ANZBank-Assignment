package com.example.anzbankassignment.domain

import com.example.anzbankassignment.data.User

interface UserRepository {
    suspend fun fetchUsers(): List<User>
    suspend fun getUserById(id: String): User?
}