package com.ulyanenko.userinfo.data.network

import com.ulyanenko.userinfo.data.model.GitHubUserDto
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    suspend fun loadUsers():List<GitHubUserDto>




}