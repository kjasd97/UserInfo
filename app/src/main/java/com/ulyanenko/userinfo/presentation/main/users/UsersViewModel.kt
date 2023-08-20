package com.ulyanenko.userinfo.presentation.main.users

import android.app.Application
import androidx.lifecycle.*
import com.ulyanenko.userinfo.data.database.AppDataBase
import com.ulyanenko.userinfo.data.database.GitHubUserDao
import com.ulyanenko.userinfo.data.database.GitHubUserEntity
import com.ulyanenko.userinfo.data.mapper.GithubUserMapper
import com.ulyanenko.userinfo.data.network.ApiFactory
import com.ulyanenko.userinfo.domain.GitHubUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao: GitHubUserDao by lazy {
        AppDataBase.getInstance(application).gitHubUserDao()
    }

    private val _users: MutableStateFlow<List<GitHubUser>?> = MutableStateFlow(null)
    val users: StateFlow<List<GitHubUser>?> = _users

    private val mapper = GithubUserMapper()


    init {
        loadGitHubUsersFromDataBase()


    }

    fun loadGitHubUsersFromInternet() {
        viewModelScope.launch {
            val response = ApiFactory.apiService.loadUsers()
            val gitUsers = mapper.mapResponseToUser(response)
            _users.value = gitUsers

            val gitUserEntities = gitUsers.map {
                GitHubUserEntity(
                    it.id,
                    it.login,
                    it.avatar_url,
                    it.url
                )
            }
            userDao.insertUsers(gitUserEntities)
        }
    }

    fun loadGitHubUsersFromDataBase() {
        viewModelScope.launch {
            val listOfUsers = userDao.getUsers().map {
                GitHubUser(
                    it.id,
                    it.login,
                    it.avatar_url,
                    it.url
                )
            }
            if (listOfUsers.isNotEmpty()) {
                _users.value = listOfUsers
            } else {
                loadGitHubUsersFromInternet()
            }
        }

    }


}