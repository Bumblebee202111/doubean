package com.doubean.ford.model

import androidx.room.PrimaryKey

data class GroupPostTag(
    @PrimaryKey
    val id: String,
    var name: String?,
)