package com.ulyanenko.userinfo.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulyanenko.userinfo.data.mapper.GithubUserMapper
import com.ulyanenko.userinfo.data.model.GitHubUserDto
import com.ulyanenko.userinfo.data.network.ApiFactory
import com.ulyanenko.userinfo.domain.GitHubUser
import kotlinx.coroutines.launch

class MyViewModel:ViewModel() {


    var users: List <GitHubUser>? by mutableStateOf(null)
        private set

    private val mapper = GithubUserMapper()

    init {
        loadRecommendations()
    }

    fun loadRecommendations() {
        viewModelScope.launch {
            val response = ApiFactory.apiService.loadUsers()
            val gitUsers = mapper.mapResponseToUser(response)
            users = gitUsers
        }
    }
}