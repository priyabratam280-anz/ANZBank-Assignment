package com.example.anzbankassignment.domain

data class User(
    val id: String,
    val name: String,
    val email: String,
    val company: String,
    val username: String,
    val address: String,
    val zip: String,
    val state: String,
    val country: String,
    val phone: String,
    val photo: String
)