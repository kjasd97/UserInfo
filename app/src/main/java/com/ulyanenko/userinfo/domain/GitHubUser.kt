package com.ulyanenko.userinfo.domain

data class GitHubUser (
    val id:Long,
    val login: String,
    val avatar_url: String,
    val url: String,
)