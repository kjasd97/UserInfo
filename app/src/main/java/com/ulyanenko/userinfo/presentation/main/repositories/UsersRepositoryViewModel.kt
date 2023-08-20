package com.ulyanenko.userinfo.presentation.main.repositories

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.ulyanenko.userinfo.data.database.AppDataBase
import com.ulyanenko.userinfo.data.database.GitHubUserDao
import com.ulyanenko.userinfo.data.database.UserRepositoryDao
import com.ulyanenko.userinfo.data.database.UserRepositoryEntity
import com.ulyanenko.userinfo.data.mapper.GithubUserMapper
import com.ulyanenko.userinfo.data.mapper.UserRepoMapper
import com.ulyanenko.userinfo.data.network.ApiFactory
import com.ulyanenko.userinfo.domain.GitHubUser
import com.ulyanenko.userinfo.domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersRepositoryViewModel(user:String, application: Application): AndroidViewModel(application) {

    private val _repos: MutableStateFlow<List<UserRepository>?> = MutableStateFlow(null)
    val repos: StateFlow<List<UserRepository>?> = _repos

    private val repDao: UserRepositoryDao by lazy {
        AppDataBase.getInstance(application).userRepositoryDao()
    }

    private val mapper = UserRepoMapper()

    init {
        loadReposFromDatabase(user)
    }

    private fun loadReposFromDatabase(user: String) {
        viewModelScope.launch {
            val cachedRepositories = repDao.getRepositoriesForUser(user).map {
                UserRepository(
                    it.id,
                    it.name,
                    it.owner
                )
            }
            if (cachedRepositories.isNotEmpty()) {
                _repos.value = cachedRepositories
            } else {
                loadRepos(user)
            }
        }
    }

    fun loadRepos(user: String) {
        viewModelScope.launch {
            val response = ApiFactory.apiService.loadUserRepositories(user)
            val repositories = mapper.mapResponseToUserRepository (response)

            val repositoryEntity = repositories.map {
                UserRepositoryEntity(
                    it.id,
                    it.name,
                    it.owner
                )
            }
            repDao.insertRepositories(repositoryEntity)

            _repos.value = repositories
        }
    }




    }
