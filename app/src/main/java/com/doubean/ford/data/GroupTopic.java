package com.doubean.ford.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;


@Entity(tableName = "group_topics")
public class GroupTopic {
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    public String topicId;
    @ColumnInfo(name = "group_id")
    public String groupId;
    @ColumnInfo(name = "tag_id")
    public String tagId;
    public String title;
    public Author author;
    @SerializedName("create_time")
    @ColumnInfo(name = "date_created")
    public Date dateCreated;
    @SerializedName("update_time")
    @ColumnInfo(name = "date_updated")
    public Date dateUpdated;
    @SerializedName("like_count")
    @ColumnInfo(name = "like_count")
    public int likeCount;
    @SerializedName("comments_count")
    @ColumnInfo(name = "comment_count")
    public int commentCount;
    public String content;
    @SerializedName("topic_tags")
    public List<GroupTopicTag> topicTags;
    @SerializedName("cover_url")
    @ColumnInfo(name = "cover_url")
    public String coverUrl;
    public String url;
    @Ignore
    public Group group;


    public String getTagName() {
        if (topicTags.isEmpty()) return null;
        return topicTags.get(0).name;
    }
}
