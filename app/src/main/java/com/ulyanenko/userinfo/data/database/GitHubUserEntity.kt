package com.ulyanenko.userinfo.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class GitHubUserEntity(
    @PrimaryKey
    val id: Long,

    val login: String,

    val avatar_url: String,

    val url: String,
): java.io.Serializable {

}
