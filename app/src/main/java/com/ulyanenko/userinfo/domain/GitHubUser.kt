package com.ulyanenko.userinfo.domain

data class GitHubUser (
    val id:Int,
    val login: String,
    val avatar_url: String,
    val url: String,
)