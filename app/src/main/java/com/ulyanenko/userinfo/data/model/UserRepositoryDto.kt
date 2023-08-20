package com.ulyanenko.userinfo.data.model

import com.google.gson.annotations.SerializedName

data class UserRepositoryDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: GitHubUserDto
)
