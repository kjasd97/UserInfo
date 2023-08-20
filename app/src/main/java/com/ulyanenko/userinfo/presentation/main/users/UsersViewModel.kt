package com.ulyanenko.userinfo.presentation.main.users

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ulyanenko.userinfo.data.database.AppDataBase
import com.ulyanenko.userinfo.data.database.GitHubUserDao
import com.ulyanenko.userinfo.data.database.GitHubUserEntity
import com.ulyanenko.userinfo.data.mapper.GithubUserMapper
import com.ulyanenko.userinfo.data.network.ApiFactory
import com.ulyanenko.userinfo.data.paging.MyPagingSourceUsers
import com.ulyanenko.userinfo.domain.GitHubUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao: GitHubUserDao by lazy {
        AppDataBase.getInstance(application).gitHubUserDao()
    }

    private val mapper = GithubUserMapper()

    val users = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { MyPagingSourceUsers(userDao, mapper ) }
    ).flow



}