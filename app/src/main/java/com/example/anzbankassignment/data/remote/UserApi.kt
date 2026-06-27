package com.example.anzbankassignment.data.remote

import com.example.anzbankassignment.data.UserDto
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}