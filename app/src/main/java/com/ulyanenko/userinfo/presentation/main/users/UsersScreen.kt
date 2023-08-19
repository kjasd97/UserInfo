package com.ulyanenko.userinfo.presentation.main.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ulyanenko.userinfo.domain.GitHubUser

@Composable
fun UserList(
    onUserClickListener: (GitHubUser) -> Unit
) {
    val viewModel: UsersViewModel = viewModel()

    val users = viewModel.users.collectAsState()

    if (users.value != null) {
        LazyColumn {
            items(users.value!!) { user ->
                UserCard(user,onUserClickListener)
            }
        }
    } else {
        Text(text = "Loading users...")
    }

}

@Composable
fun UserCard(user: GitHubUser,onUserClickListener: (GitHubUser) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onUserClickListener(user)
            },
        elevation = 4.dp
    ) {
        Row( modifier = Modifier
            .padding(10.dp) ) {
            AsyncImage(
                model = user.avatar_url,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentDescription = null
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Username: ${user.login}",
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "GitHub : ${user.url}",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Serif
                )

            }
        }

    }
}
