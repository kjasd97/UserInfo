package com.ulyanenko.userinfo.data.paging

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ulyanenko.userinfo.data.database.GitHubUserDao
import com.ulyanenko.userinfo.data.database.GitHubUserEntity
import com.ulyanenko.userinfo.data.mapper.GithubUserMapper
import com.ulyanenko.userinfo.data.network.ApiFactory
import com.ulyanenko.userinfo.data.network.ApiService
import com.ulyanenko.userinfo.domain.GitHubUser
import kotlinx.coroutines.launch

class MyPagingSourceUsers(
    private val dao: GitHubUserDao,
    private val mapper: GithubUserMapper
) : PagingSource<Int, GitHubUser>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubUser> {
        try {
            val nextPageNumber = params.key ?: 1
            val data = loadGitHubUsersFromDataBase()

            // Return the data as LoadResult
            return LoadResult.Page(
                data = data,
                prevKey = nextPageNumber -1,
                nextKey = nextPageNumber + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private suspend fun loadGitHubUsersFromInternet(): List<GitHubUser> {
        val response = ApiFactory.apiService.loadUsers()
        val gitUsers = mapper.mapResponseToUser(response)

        val gitUserEntities = gitUsers.map {
            GitHubUserEntity(
                it.id,
                it.login,
                it.avatar_url,
                it.url
            )
        }
        dao.insertUsers(gitUserEntities)

        return gitUsers
    }

    suspend fun loadGitHubUsersFromDataBase(): List<GitHubUser> {
        val listOfUsers = dao.getUsers().map {
            GitHubUser(
                it.id,
                it.login,
                it.avatar_url,
                it.url
            )
        }
        return if (listOfUsers.isNotEmpty()) {
            listOfUsers
        } else {
            loadGitHubUsersFromInternet()
        }

    }

    override fun getRefreshKey(state: PagingState<Int, GitHubUser>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}