package com.ulyanenko.userinfo.data.database

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.ulyanenko.userinfo.data.model.GitHubUserDto

@Entity(tableName = "repositories")
data class UserRepositoryEntity(

    @PrimaryKey
    val id: Long,

    val name: String,

    @Embedded(prefix = "owner_")
    val owner: GitHubUserDto
) : java.io.Serializable {

}