package com.example.anzbankassignment.data

import com.example.anzbankassignment.domain.User

data class UserDto(
    val id: Int,
    val name: String,
    val company: String,
    val username: String,
    val email: String,
    val address: String,
    val zip: String,
    val state: String,
    val country: String,
    val phone: String,
    val photo: String
) {
    fun toDomain() = User(
        id = id.toString(),
        name = name,
        email = email,
        company = company,
        username = username,
        address = address,
        zip = zip,
        state = state,
        country = country,
        phone = phone,
        photo = photo
    )
}
