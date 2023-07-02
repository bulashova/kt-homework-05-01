package ru.netology

data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val text: String = "text",
    val date: Int = 101023,
    val friendsOnly: Boolean = false,
    val isFavorite: Boolean = false,
    val markedAsAds: Boolean = false,
    val canEdit: Boolean = false,
    val canDelete: Boolean = false,

    val comments: Comments = Comments(),
    val likes: Likes = Likes()
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
    val canPublish: Boolean = false
)

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = ++lastId)
        return posts.last()
    }

    fun update(postUpdate: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (postUpdate.id == post.id) {
                posts[index] = postUpdate

                println(posts[index])
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }
}

fun main() {
    println(WallService.add(Post()))
    println(WallService.add(Post()))
    println(WallService.add(Post()))
    println("")
    if (WallService.update(Post(id = 2, text = "content", friendsOnly = true)))
        println("Пост обновлен")
    else println("Пост не найден")
}