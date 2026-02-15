package bctci.heaps

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class Task37_4 {

    class TopSongs(private val k: Int) {
        private val queue = PriorityQueue<Pair<String, Int>>(compareByDescending { it.second })
        private val map = mutableMapOf<String, Int>()
        fun registerPlays(title: String, plays: Int) {
            map[title] = (map[title] ?: 0) + plays
            queue.add(title to requireNotNull(map[title]))
        }

        fun topK(): List<String> {
            val result = mutableListOf<String>()
            while (queue.isNotEmpty() && result.size < k) {
                val item = queue.poll()
                if (isValid(item)) result.add(item.first)
            }
            return result
        }

        private fun isValid(item: Pair<String, Int>): Boolean {
            return map[item.first] == item.second
        }
    }
}


private class Task37_4Test {

    @Test
    fun test1() {
        val topSongs = Task37_4.TopSongs(3).apply {
            registerPlays("Boolean Rhapsody", 100)
            registerPlays("Boolean Rhapsody", 193)
            registerPlays("Coding In The Deep", 75)
            registerPlays("Coding In The Deep", 75)
            registerPlays("All About That Base Case", 200)
            registerPlays("All About That Base Case", 90)
            registerPlays("All About That Base Case", 1)
            registerPlays("Here Comes The Bug", 223)
            registerPlays("Oops! I Broke Prod Again", 274)
            registerPlays("All the Single Brackets", 132)
        }
        val expected = listOf("All About That Base Case", "Boolean Rhapsody", "Oops! I Broke Prod Again").sorted()
        Assertions.assertEquals(expected, topSongs.topK().sorted())
    }

    // Test with fewer songs than k
    @Test
    fun test2() {
        val topSongs = Task37_4.TopSongs(5).apply {
            registerPlays("Song A", 100)
            registerPlays("Song B", 200)
        }
        val expected = listOf("Song A", "Song B").sorted()
        Assertions.assertEquals(expected, topSongs.topK().sorted())
    }

    // Test with exact k songs
    @Test
    fun test3() {
        val topSongs = Task37_4.TopSongs(3).apply {
            registerPlays("Song A", 100)
            registerPlays("Song B", 200)
            registerPlays("Song C", 300)
        }
        val expected = listOf("Song A", "Song B", "Song C").sorted()
        Assertions.assertEquals(expected, topSongs.topK().sorted())
    }

    // Test with ties in play counts
    @Test
    fun test4() {
        val topSongs = Task37_4.TopSongs(2).apply {
            registerPlays("Song A", 100)
            registerPlays("Song B", 100)
            registerPlays("Song C", 100)
            registerPlays("Song D", 100)
        }
        Assertions.assertEquals(2, topSongs.topK().size)
    }
}