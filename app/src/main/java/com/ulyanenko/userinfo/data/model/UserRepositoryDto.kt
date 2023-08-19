package com.ulyanenko.userinfo.data.model

import com.google.gson.annotations.SerializedName

data class UserRepositoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: GitHubUserDto
)
