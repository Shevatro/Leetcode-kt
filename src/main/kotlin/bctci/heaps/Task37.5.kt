package bctci.heaps

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.Collections
import java.util.PriorityQueue

class Task37_5 {

    class PopularSongs {
        private val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())
        private val minHeap = PriorityQueue<Int>()
        private val cache = mutableMapOf<String, Int>()

        fun registerPlays(title: String, plays: Int) {
            cache[title] = plays
            maxHeap.add(plays)
            minHeap.add(maxHeap.poll())
            if (minHeap.size > maxHeap.size) {
                maxHeap.add(minHeap.poll())
            }
        }

        fun isPopular(title: String): Boolean {
            if (cache[title] == null) return false
            return requireNotNull(cache[title]) > getMedian()
        }

        private fun getMedian(): Double {
            return if ((minHeap.size + maxHeap.size) % 2 == 0) {
                (minHeap.peek() + maxHeap.peek()) / 2.0
            } else if (minHeap.size > maxHeap.size) {
                minHeap.peek().toDouble()
            } else {
                maxHeap.peek().toDouble()
            }
        }
    }
}


private class Task37_5Test {
    @Test
    fun `Example from the book`() {
        val popularSongs = Task37_5.PopularSongs()
        popularSongs.registerPlays("Boolean Rhapsody", 193)
        Assertions.assertEquals(false, popularSongs.isPopular("Boolean Rhapsody"))
        popularSongs.apply {
            registerPlays("Coding In The Deep", 140)
            registerPlays("All the Single Brackets", 132)
        }
        Assertions.assertEquals(true, popularSongs.isPopular("Boolean Rhapsody"))
        Assertions.assertEquals(false, popularSongs.isPopular("Coding In The Deep"))
        Assertions.assertEquals(false, popularSongs.isPopular("All the Single Brackets"))
        popularSongs.apply {
            registerPlays("All About That Base Case", 291)
            registerPlays("Oops! I Broke Prod Again", 274)
            registerPlays("Here Comes The Bug", 223)
        }
        Assertions.assertEquals(false, popularSongs.isPopular("Boolean Rhapsody"))
        Assertions.assertEquals(true, popularSongs.isPopular("Here Comes The Bug"))
    }

    @Test
    fun `Test with no songs`() {
        val popularSongs = Task37_5.PopularSongs()
        Assertions.assertEquals(false, popularSongs.isPopular("Nonexistent Song"))
    }

    @Test
    fun `Test with one song`() {
        val popularSongs = Task37_5.PopularSongs().apply {
            registerPlays("Single Song", 100)
        }
        Assertions.assertEquals(false, popularSongs.isPopular("Single Song"))
    }

    @Test
    fun `Test with two songs`() {
        val popularSongs = Task37_5.PopularSongs().apply {
            registerPlays("Song A", 100)
            registerPlays("Song B", 200)
        }
        Assertions.assertEquals(false, popularSongs.isPopular("Song A"))
        Assertions.assertEquals(true, popularSongs.isPopular("Song B"))
    }

    @Test
    fun `Test with three songs`() {
        val popularSongs = Task37_5.PopularSongs().apply {
            registerPlays("Song A", 100)
            registerPlays("Song B", 200)
            registerPlays("Song C", 150)
        }
        Assertions.assertEquals(false, popularSongs.isPopular("Song A"))
        Assertions.assertEquals(true, popularSongs.isPopular("Song B"))
        Assertions.assertEquals(false, popularSongs.isPopular("Song C"))
    }
}