package com.ulyanenko.userinfo.presentation.main.repositories

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.ulyanenko.userinfo.domain.GitHubUser
import com.ulyanenko.userinfo.domain.UserRepository
import com.ulyanenko.userinfo.presentation.main.users.UserCard
import com.ulyanenko.userinfo.presentation.main.users.UsersViewModel

@Composable
fun UserRepositoriesScreen(userLogin: String, application:Application) {

    val viewModel: UsersRepositoryViewModel = viewModel(
        factory = RepViewModelFactory(userLogin,application)
    )

    val repos: LazyPagingItems<UserRepository> = viewModel.repositories.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Repositories of user: $userLogin")
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 72.dp
            )
        ) {
            items(repos.itemCount) { index ->
                repos[index]?.let {
                    RepositoryItem(repo = it)
                }
            }
        }
    }
}

@Composable
private fun RepositoryItem(repo: UserRepository) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 4.dp
            )
    ) {
        AsyncImage(
            model = repo.owner.avatar_url,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "Owner is: ${repo.owner.login}",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Repository name is: ${repo.name}",
                fontSize = 12.sp
            )

        }
    }
}