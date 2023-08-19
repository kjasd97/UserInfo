package com.ulyanenko.userinfo.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ulyanenko.userinfo.presentation.main.repositories.UserRepositoriesScreen
import com.ulyanenko.userinfo.presentation.main.users.UserList
import com.ulyanenko.userinfo.presentation.main.users.UsersViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
         MainScreen(application)
        }
    }


}