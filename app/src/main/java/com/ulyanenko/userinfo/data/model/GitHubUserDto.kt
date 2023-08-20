package com.ulyanenko.userinfo.data.model

import com.google.gson.annotations.SerializedName

data class GitHubUserDto(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Long,
    @SerializedName("avatar_url")  val avatar_url: String,
    @SerializedName("url") val url: String,
)