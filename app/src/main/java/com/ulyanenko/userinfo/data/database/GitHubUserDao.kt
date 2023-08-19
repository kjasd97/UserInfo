package com.ulyanenko.userinfo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ulyanenko.userinfo.domain.GitHubUser

@Dao
interface GitHubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<GitHubUserEntity>)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<GitHubUserEntity>

}