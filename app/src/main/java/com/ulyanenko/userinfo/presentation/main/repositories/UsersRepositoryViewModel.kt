package com.ulyanenko.userinfo.presentation.main.repositories

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ulyanenko.userinfo.data.database.AppDataBase
import com.ulyanenko.userinfo.data.database.GitHubUserDao
import com.ulyanenko.userinfo.data.database.UserRepositoryDao
import com.ulyanenko.userinfo.data.database.UserRepositoryEntity
import com.ulyanenko.userinfo.data.mapper.GithubUserMapper
import com.ulyanenko.userinfo.data.mapper.UserRepoMapper
import com.ulyanenko.userinfo.data.network.ApiFactory
import com.ulyanenko.userinfo.data.paging.MyPagingSourceRepositories
import com.ulyanenko.userinfo.data.paging.MyPagingSourceUsers
import com.ulyanenko.userinfo.domain.GitHubUser
import com.ulyanenko.userinfo.domain.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersRepositoryViewModel(user:String, application: Application): AndroidViewModel(application) {


    private val repDao: UserRepositoryDao by lazy {
        AppDataBase.getInstance(application).userRepositoryDao()
    }

    private val mapper = UserRepoMapper()

    val repositories = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { MyPagingSourceRepositories(user,repDao, mapper ) }
    ).flow



    }
