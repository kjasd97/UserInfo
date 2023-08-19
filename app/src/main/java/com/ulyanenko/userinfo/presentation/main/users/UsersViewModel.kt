package com.ulyanenko.userinfo.presentation.main.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulyanenko.userinfo.data.mapper.GithubUserMapper
import com.ulyanenko.userinfo.data.network.ApiFactory
import com.ulyanenko.userinfo.domain.GitHubUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {

    private val _users: MutableStateFlow<List<GitHubUser>?> = MutableStateFlow(null)
    val users: StateFlow<List<GitHubUser>?> = _users

    private val mapper = GithubUserMapper()

    init {
        loadRecommendations()
    }

    fun loadRecommendations() {
        viewModelScope.launch {
            val response = ApiFactory.apiService.loadUsers()
            val gitUsers = mapper.mapResponseToUser(response)
            _users.value = gitUsers
        }
    }

//    private val _users = MutableLiveData<List<GitHubUser>>()
//    val users: LiveData<List<GitHubUser>> = _users
//
//    private val mapper = GithubUserMapper()
//
//    init {
//        loadRecommendations()
//    }
//
//     fun loadRecommendations() {
//        viewModelScope.launch {
//            val response = ApiFactory.apiService.loadUsers()
//            val gitUsers = mapper.mapResponseToUser(response)
//            _users.value = gitUsers
//        }
//    }


}