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
        val result = WallService.add(Post()).id
        assertEquals(1, result)
    }

    @Test
    fun updateExisting() {

        WallService.add(Post())
        WallService.add(Post())
        WallService.add(Post())

        val result = WallService.update(Post(id = 2, text = "content", friendsOnly = true))
        assertTrue(result)
    }

    @Test
    fun updateNoExisting() {

        WallService.add(Post())
        WallService.add(Post())
        WallService.add(Post())

        val result = WallService.update(Post(id = 8, text = "content", friendsOnly = true))
        assertFalse(result)
    }
}