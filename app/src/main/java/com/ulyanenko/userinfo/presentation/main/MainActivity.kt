package com.ulyanenko.userinfo.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.google.android.engage.common.datamodel.Image
import com.ulyanenko.userinfo.domain.GitHubUser
import com.ulyanenko.userinfo.ui.theme.UserInfoTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserList()
        }
    }

    @Composable
    fun UserList(viewModel: MyViewModel = viewModel()) {
        val users = viewModel.users
        if (users != null) {
            LazyColumn {
                items(users) { user ->
                    UserCard(user)
                }
            }
        } else {
            Text(text = "Loading users...")
        }
    }

    @Composable
    fun UserCard(user: GitHubUser) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            elevation = 4.dp
        ) {
            Row(modifier = Modifier.padding(16.dp)) {

                AsyncImage(
                    model = user.avatar_url,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column() {
                    Text(
                        text = "User login: ${user.login}",
                        fontSize = 17.sp,
                        fontFamily = FontFamily.Serif
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "User GitHub: ${user.url}",
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Serif
                    )
                }


            }
        }
    }


}