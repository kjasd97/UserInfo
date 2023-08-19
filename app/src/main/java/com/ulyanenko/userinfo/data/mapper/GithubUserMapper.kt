package com.ulyanenko.userinfo.data.mapper

import com.ulyanenko.userinfo.data.model.GitHubUserDto
import com.ulyanenko.userinfo.domain.GitHubUser

class GithubUserMapper {

    fun mapResponseToUser(gitHubUsersDto: List <GitHubUserDto>): List<GitHubUser>{

        val result = mutableListOf<GitHubUser>()
        for (userDto in gitHubUsersDto) {
            val gitHubUser = GitHubUser(
                login = userDto.login,
                id = userDto.id,
                node_id = userDto.node_id,
                avatar_url = userDto.avatar_url,
                gravatar_id = userDto.gravatar_id,
                url = userDto.url,
                html_url = userDto.html_url,
                followers_url = userDto.followers_url,
                following_url = userDto.following_url,
                gists_url = userDto.gists_url,
                starred_url = userDto.starred_url,
                subscriptions_url = userDto.subscriptions_url,
                organizations_url = userDto.organizations_url,
                repos_url = userDto.repos_url,
                events_url = userDto.events_url,
                received_events_url = userDto.received_events_url,
                type = userDto.type,
                site_admin = userDto.site_admin
            )
            result.add(gitHubUser)
        }

        return result
    }

}