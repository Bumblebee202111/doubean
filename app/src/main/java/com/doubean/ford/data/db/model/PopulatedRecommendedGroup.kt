package com.doubean.ford.data.db.model

import androidx.room.Embedded
import androidx.room.Relation
import com.doubean.ford.model.RecommendedGroupItem
import com.doubean.ford.model.RecommendedGroupItemGroup


class PopulatedRecommendedGroup(
    @Embedded
    val entity: RecommendedGroupEntity,

    @Relation(
        parentColumn = "group_id",
        entityColumn = "id",
        entity = GroupEntity::class
    )
    val group: PopulatedRecommendedGroupItemGroup,
    @Relation(
        parentColumn = "group_id",
        entityColumn = "id",
        entity = PostEntity::class
    )
    val posts: List<PopulatedPostItem>,
)

data class PopulatedRecommendedGroupItemGroup(
    @Embedded
    val partialEntity: RecommendedGroupItemGroupPartialEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "group_id"
    )
    val followedGroup: FollowedGroupEntity?,
)


fun PopulatedRecommendedGroup.asExternalModel() = RecommendedGroupItem(
    no = entity.no,
    group = group.asExternalModel(),
    //posts = posts.map { it.asExternalModel() },
)

fun PopulatedRecommendedGroupItemGroup.asExternalModel() = RecommendedGroupItemGroup(
    id = partialEntity.id,
    name = partialEntity.name,
    memberCount = partialEntity.memberCount,
    memberName = partialEntity.memberName,
    postCount = partialEntity.postCount,
    shareUrl = partialEntity.shareUrl,
    url = partialEntity.url,
    uri = partialEntity.uri,
    avatarUrl = partialEntity.avatarUrl,
    color = partialEntity.color,
    shortDescription = partialEntity.shortDescription,
    isFollowed = followedGroup != null
)
