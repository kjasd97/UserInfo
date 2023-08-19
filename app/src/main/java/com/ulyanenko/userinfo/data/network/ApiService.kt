package com.ulyanenko.userinfo.data.network

import com.ulyanenko.userinfo.data.model.GitHubUserDto
import com.ulyanenko.userinfo.data.model.UserRepositoryDto
import com.ulyanenko.userinfo.domain.UserRepository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/users")
    suspend fun loadUsers():List<GitHubUserDto>

    @GET("users/{login}/repos")
    suspend fun loadUserRepositories(@Path("login") login : String):List<UserRepositoryDto>




}