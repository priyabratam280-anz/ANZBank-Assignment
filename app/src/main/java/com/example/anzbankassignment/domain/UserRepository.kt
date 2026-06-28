package com.example.anzbankassignment.domain

interface UserRepository {
    suspend fun fetchUsers(): List<User>
    suspend fun getUserById(id: String): User?
}
