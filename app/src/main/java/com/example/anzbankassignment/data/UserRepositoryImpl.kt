package com.example.anzbankassignment.data

import com.example.anzbankassignment.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl  @Inject constructor(private val api: UserApi) : UserRepository {
    override suspend fun fetchUsers(): List<User> = api.getUsers().map { it.toDomain() }

    override suspend fun getUserById(id: String): User? =
        fetchUsers().firstOrNull { it.id == id }
}