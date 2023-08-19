package com.ulyanenko.userinfo.data.mapper

import com.ulyanenko.userinfo.data.model.GitHubUserDto
import com.ulyanenko.userinfo.data.model.UserRepositoryDto
import com.ulyanenko.userinfo.domain.GitHubUser
import com.ulyanenko.userinfo.domain.UserRepository

class UserRepoMapper {

    fun mapResponseToUserRepository(reposDto: List<UserRepositoryDto>): List<UserRepository> {

        val result = mutableListOf<UserRepository>()
        for (userRepoDto in reposDto) {
            val repo = UserRepository(
                name = userRepoDto.name,
                owner = userRepoDto.owner,
                id = userRepoDto.id
            )
            result.add(repo)
        }

        return result
    }

}