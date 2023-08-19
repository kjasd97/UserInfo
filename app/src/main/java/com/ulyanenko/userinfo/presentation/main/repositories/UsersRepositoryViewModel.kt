package com.ulyanenko.userinfo.presentation.main.repositories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulyanenko.userinfo.data.mapper.GithubUserMapper
import com.ulyanenko.userinfo.data.mapper.UserRepoMapper
import com.ulyanenko.userinfo.data.network.ApiFactory
import com.ulyanenko.userinfo.domain.GitHubUser
import com.ulyanenko.userinfo.domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersRepositoryViewModel(user:String): ViewModel() {

    private val _repos: MutableStateFlow<List<UserRepository>?> = MutableStateFlow(null)
    val repos: StateFlow<List<UserRepository>?> = _repos



    private val mapper = UserRepoMapper()

    init {
        loadRepos(user)
    }

    fun loadRepos(user: String) {
        viewModelScope.launch {
            val response = ApiFactory.apiService.loadUserRepositories(user)
            val repositories = mapper.mapResponseToUserRepository (response)
            _repos.value = repositories
        }
    }


//    private val _repositories = MutableLiveData<List<UserRepository>>()
//    val repositories: LiveData<List<UserRepository>> = _repositories
//
//    private val mapper = UserRepoMapper()
//
//
//    init {
//        loadRepos(user)
//    }
//
//    private fun loadRepos(user: String) {
//        viewModelScope.launch {
//            val response = ApiFactory.apiService.loadUserRepositories(user)
//            val repositories = mapper.mapResponseToUserRepository(response)
//            _repositories.value = repositories
//
//        }
//
//        }

    }
