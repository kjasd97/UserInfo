package com.ulyanenko.userinfo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserRepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<UserRepositoryEntity>)

    @Query("SELECT * FROM repositories WHERE owner_login = :ownerId")
    suspend fun getRepositoriesForUser(ownerId: String): List<UserRepositoryEntity>
}