package com.example.anzbankassignment.data

import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}