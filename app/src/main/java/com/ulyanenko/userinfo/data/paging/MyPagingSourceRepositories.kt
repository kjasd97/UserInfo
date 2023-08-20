package com.ulyanenko.userinfo.data.paging

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ulyanenko.userinfo.data.database.GitHubUserDao
import com.ulyanenko.userinfo.data.database.GitHubUserEntity
import com.ulyanenko.userinfo.data.database.UserRepositoryDao
import com.ulyanenko.userinfo.data.database.UserRepositoryEntity
import com.ulyanenko.userinfo.data.mapper.GithubUserMapper
import com.ulyanenko.userinfo.data.mapper.UserRepoMapper
import com.ulyanenko.userinfo.data.network.ApiFactory
import com.ulyanenko.userinfo.data.network.ApiService
import com.ulyanenko.userinfo.domain.GitHubUser
import com.ulyanenko.userinfo.domain.UserRepository
import kotlinx.coroutines.launch

class MyPagingSourceRepositories(
    private val userName: String,
    private val dao: UserRepositoryDao,
    private val mapper: UserRepoMapper
) : PagingSource<Int, UserRepository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserRepository> {
        try {
            val nextPageNumber = params.key ?: 1
            val data = loadReposFromDatabase(userName)

                // Return the data as LoadResult
                return LoadResult.Page(
                    data = data,
                    prevKey = nextPageNumber - 1,
                    nextKey = nextPageNumber + 1
                )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    suspend fun loadReposFromDatabase(user: String): List<UserRepository> {
        val cachedRepositories = dao.getRepositoriesForUser(user).map {
            UserRepository(
                it.id,
                it.name,
                it.owner
            )
        }
        return if (cachedRepositories.isNotEmpty()) {
            cachedRepositories
        } else {
            loadRepos(user)
        }
    }

    suspend fun loadRepos(user: String): List<UserRepository> {
        val response = ApiFactory.apiService.loadUserRepositories(user)
        val repositories = mapper.mapResponseToUserRepository(response)

        val repositoryEntity = repositories.map {
            UserRepositoryEntity(
                it.id,
                it.name,
                it.owner
            )
        }
        dao.insertRepositories(repositoryEntity)

        return repositories
    }

    override fun getRefreshKey(state: PagingState<Int, UserRepository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }



}