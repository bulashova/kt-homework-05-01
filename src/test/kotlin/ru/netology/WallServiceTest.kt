package ru.netology

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addExisting() {
        val result = WallService.add(Post(replyOwnerId = null, replyPostId = null)).id
        assertEquals(1, result)
    }

    @Test
    fun updateExisting() {

        WallService.add(Post(replyOwnerId = null, replyPostId = null))
        WallService.add(Post(replyOwnerId = 1, replyPostId = 11))
        WallService.add(Post(replyOwnerId = null, replyPostId = null))

        val result = WallService.update(
            Post(
                id = 2, text = "content", friendsOnly = true,
                replyOwnerId = null, replyPostId = null
            )
        )
        assertTrue(result)
    }

    @Test
    fun updateNoExisting() {

        WallService.add(Post(replyOwnerId = null, replyPostId = null))
        WallService.add(Post(replyOwnerId = 1, replyPostId = 11))
        WallService.add(Post(replyOwnerId = null, replyPostId = null))

        val result = WallService.update(
            Post(
                id = 8, text = "content", friendsOnly = true,
                replyOwnerId = null, replyPostId = null
            )
        )
        assertFalse(result)
    }
}