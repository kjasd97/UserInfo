package com.ulyanenko.userinfo.data.mapper

import com.ulyanenko.userinfo.data.model.GitHubUserDto
import com.ulyanenko.userinfo.domain.GitHubUser

class GithubUserMapper {

    fun mapResponseToUser(gitHubUsersDto: List<GitHubUserDto>): List<GitHubUser> {

        val result = mutableListOf<GitHubUser>()
        for (userDto in gitHubUsersDto) {
            val gitHubUser = GitHubUser(
                login = userDto.login,
                avatar_url = userDto.avatar_url,
                url = userDto.url,
                id = userDto.id
                )
            result.add(gitHubUser)
        }

        return result
    }

}