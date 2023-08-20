package com.ulyanenko.userinfo.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class GitHubUserEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: Long,

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatar_url: String,

    @SerializedName("url")
    val url: String,
): java.io.Serializable {

}
