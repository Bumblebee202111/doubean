package com.doubean.ford.data.db.model

import androidx.room.*
import java.time.LocalDateTime

/**
 * Class officially known as topic/话题
 */
@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val id: String,

    val title: String,

    @ColumnInfo("author_id")
    val authorId: String,

    @ColumnInfo("created")
    val created: LocalDateTime,

    @ColumnInfo("last_updated")
    val lastUpdated: LocalDateTime,

    @ColumnInfo("like_count")
    val likeCount: Int? = null,

    @ColumnInfo("reaction_count")
    val reactionCount: Int? = null,

    @ColumnInfo("repost_count")
    val repostCount: Int? = null,

    @ColumnInfo("save_count")
    val saveCount: Int? = null,

    @ColumnInfo("comment_count")
    val commentCount: Int? = null,

    @ColumnInfo("short_content")
    val shortContent: String? = null,

    val content: String?,

    @ColumnInfo("cover_url")
    val coverUrl: String? = null,

    val url: String,

    val uri: String,

    @ColumnInfo("group_id")
    val groupId: String,
)



