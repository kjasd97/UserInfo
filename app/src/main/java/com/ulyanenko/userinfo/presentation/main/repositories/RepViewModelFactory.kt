package com.ulyanenko.userinfo.presentation.main.repositories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RepViewModelFactory(private val user:String, private val application: Application):  ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersRepositoryViewModel(user,application) as T
    }
}
