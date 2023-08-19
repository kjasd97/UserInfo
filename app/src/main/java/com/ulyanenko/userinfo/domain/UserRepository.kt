package com.ulyanenko.userinfo.domain

import com.google.gson.annotations.SerializedName
import com.ulyanenko.userinfo.data.model.GitHubUserDto

data class UserRepository(
    val id:Int,
    val name: String,
    val owner: GitHubUserDto
)
