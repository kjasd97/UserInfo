package com.ulyanenko.userinfo.presentation.main.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RepViewModelFactory(private val user:String):  ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersRepositoryViewModel(user) as T
    }
}
