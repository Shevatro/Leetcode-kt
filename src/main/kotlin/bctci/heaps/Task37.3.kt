package bctci.heaps

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.PriorityQueue

class Task37_3 {

    class TopSongs(private val k: Int) {
        private val minHeap = PriorityQueue<Pair<String, Int>>(compareBy { it.second })
        fun registerPlays(title: String, plays: Int) {
            minHeap.add(title to plays)
            if (minHeap.size > k) minHeap.poll()
        }

        fun topK(): List<String> {
            val result = mutableListOf<String>()
            while (minHeap.isNotEmpty()) result.add(minHeap.poll().first)
            return result
        }
    }
}


private class Task37_3Test {

    @Test
    fun test1() {
        val topSongs = Task37_3.TopSongs(3).apply {
            registerPlays("Boolean Rhapsody", 193)
            registerPlays("Coding In The Deep", 146)
        }
        val expected1 = listOf("Coding In The Deep", "Boolean Rhapsody").sorted()
        Assertions.assertEquals(expected1, topSongs.topK().sorted())
        topSongs.apply {
            registerPlays("All About That Base Case", 291)
            registerPlays("Here Comes The Bug", 223)
            registerPlays("Oops! I Broke Prod Again", 274)
            registerPlays("All the Single Brackets", 132)
        }
        val expected2 = listOf("All About That Base Case", "Here Comes The Bug", "Oops! I Broke Prod Again").sorted()
        Assertions.assertEquals(expected2, topSongs.topK().sorted())
    }

    // Test with fewer songs than k
    @Test
    fun test2() {
        val topSongs = Task37_3.TopSongs(5).apply {
            registerPlays("Song A", 100)
            registerPlays("Song B", 200)
        }
        val expected = listOf("Song A", "Song B").sorted()
        Assertions.assertEquals(expected, topSongs.topK().sorted())
    }

    // Test with exact k songs
    @Test
    fun test3() {
        val topSongs = Task37_3.TopSongs(3).apply {
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
        val topSongs = Task37_3.TopSongs(2).apply {
            registerPlays("Song A", 100)
            registerPlays("Song B", 100)
            registerPlays("Song C", 100)
            registerPlays("Song D", 100)
        }
        Assertions.assertEquals(2, topSongs.topK().size)
    }
}