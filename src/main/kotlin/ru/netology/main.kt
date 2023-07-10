package ru.netology

data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 101023,
    val text: String = "text",
    val replyOwnerId: Int?,
    val replyPostId: Int?,
    val friendsOnly: Boolean = false,
    val isFavorite: Boolean = false,
    val markedAsAds: Boolean = false,
    val canEdit: Boolean = false,
    val canDelete: Boolean = false,

    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val copyright: Copyright = Copyright(),
    val reposts: Reposts = Reposts(),
)

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false,
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = false,
    val canPublish: Boolean = false,
)

data class Copyright(
    val id: Int = 0,
    val link: String = "link",
    val name: String = "name",
    val type: String = "type",
)

data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false,
)

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        posts += post.copy(
            id = ++lastId, comments = post.comments.copy(), likes = post.likes.copy(),
            copyright = post.copyright.copy(), reposts = post.reposts.copy()
        )
        return posts.last()
    }

    fun update(postUpdate: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (postUpdate.id == post.id) {
                posts[index] = postUpdate.copy(
                    comments = postUpdate.comments.copy(), likes = postUpdate.likes.copy(),
                    copyright = postUpdate.copyright.copy(), reposts = postUpdate.reposts.copy()
                )
                return true
            }
        }
        return false
    }

    fun printAll() {
        for (post in posts) {
            println(post)
        }
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }
}

fun main() {
    WallService.add(Post(replyOwnerId = null, replyPostId = null))
    WallService.add(Post(replyOwnerId = 1, replyPostId = 11))
    WallService.add(Post(replyOwnerId = null, replyPostId = null))
    WallService.printAll()
    println()
    if (WallService.update(
            Post(
                id = 2, text = "content", friendsOnly = true, replyOwnerId = null,
                replyPostId = null, likes = Likes(count = 10)
            )
        )
    ) {
        println("Пост обновлен")
        WallService.printAll()
    } else println("Пост не найден")
}