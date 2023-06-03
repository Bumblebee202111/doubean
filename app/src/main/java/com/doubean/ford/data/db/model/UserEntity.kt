package com.doubean.ford.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doubean.ford.model.User

@Entity("users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val type: String,
    val name: String,
    @ColumnInfo("avatar_url")
    val avatarUrl: String,
)

fun UserEntity.asExternalModel() = User(
    id = id, type = type, name = name, avatarUrl = avatarUrl
)