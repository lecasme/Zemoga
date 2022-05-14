package com.leo.zemoga.presentation.utils

import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.domain.models.Post

fun PostEntity.toModel(): Post {
    return Post(
        this.userId,
        this.id,
        this.title,
        this.body,
        this.favourite
    )
}

fun Post.toEntity(): PostEntity {
    return PostEntity(
        this.id,
        this.userId,
        this.title,
        this.body,
        this.favourite
    )
}

fun List<PostEntity>.toListedPosts(): List<Post> {
    val posts = mutableListOf<Post>()
    this.forEach{ posts.add(it.toModel()) }
    return posts
}