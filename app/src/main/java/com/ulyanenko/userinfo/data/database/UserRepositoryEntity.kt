package com.ulyanenko.userinfo.data.database

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.ulyanenko.userinfo.data.model.GitHubUserDto

@Entity(tableName = "repositories")
data class UserRepositoryEntity(

    @PrimaryKey
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("owner")
    @Embedded(prefix = "owner_")
    val owner: GitHubUserDto
) : java.io.Serializable {

}