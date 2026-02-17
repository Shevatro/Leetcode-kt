package bctci.heaps

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.PriorityQueue
import java.util.stream.Stream
import kotlin.math.min

class Task37_6 {

    fun findMostListenedAcrossGenres(genres: List<List<Pair<String, Int>>>, k: Int): List<String> {
        return Solution(genres, k).findMostListenedAcrossGenres()
    }

    private class Solution(
        private val genres: List<List<Pair<String, Int>>>,
        private val k: Int
    ) {
        private val minHeap = PriorityQueue<Pair<String, Int>>(compareBy { it.second })
        fun findMostListenedAcrossGenres(): List<String> {
            for (genre in genres) {
                for (i in 0 until min(k, genre.size)) {
                    minHeap.add(genre[i])
                    println("i $i maxHeap: $minHeap")
                    if (minHeap.size > k) {
                        minHeap.poll()
                    }
                }
            }
            return heapToList()
        }

        fun heapToList(): List<String> {
            val result = mutableListOf<String>()
            while (minHeap.isNotEmpty() && result.size < k) {
                result.add(minHeap.poll().first)
            }
            return result.reversed()
        }
    }
}


private class Task37_6Test {
    private val task = Task37_6()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: List<List<Pair<String, Int>>>, input2: Int, expected: List<String>) {
        Assertions.assertEquals(expected, task.findMostListenedAcrossGenres(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                // Standard mixed case
                Arguments.of(
                    listOf(
                        listOf("Coding In The Deep" to 123, "Someone Like GNU" to 99, "Hello World" to 98), listOf("Ring Of Firewalls" to 217),
                        listOf("Boolean Rhapsody" to 184, "Merge Together" to 119, "Hey Queue" to 102)
                    ), 5,
                    listOf("Ring Of Firewalls", "Boolean Rhapsody", "Coding In The Deep", "Merge Together", "Hey Queue")
                ),

                // Test with fewer songs than k
                Arguments.of(listOf(listOf("Song A" to 100), listOf("Song B" to 200)), 5, listOf("Song B", "Song A")),

                // Test with exact k songs
                Arguments.of(
                    listOf(listOf("Song A" to 100), listOf("Song B" to 200), listOf("Song C" to 300)), 3,
                    listOf("Song C", "Song B", "Song A")
                ),
                // Test with empty genres
                Arguments.of(emptyList<List<Pair<String, Int>>>(), 3, emptyList<String>()),

                // Test with k=1
                Arguments.of(
                    listOf(
                        listOf("Song A" to 50, "Song B" to 30),
                        listOf("Song C" to 100, "Song D" to 80),
                        listOf("Song E" to 75)
                    ), 1, listOf("Song C")
                ),

                // Test with descending play counts within genres
                Arguments.of(
                    listOf(
                        listOf("Song A" to 300, "Song B" to 200, "Song C" to 100),
                        listOf("Song D" to 250, "Song E" to 150, "Song F" to 50)
                    ), 4,
                    listOf("Song A", "Song D", "Song B", "Song E")
                )
            )
        }
    }

    @Test
    fun `test with ties in play counts`() {
        val genres = listOf(listOf("Song A" to 100), listOf("Song B" to 100), listOf("Song C" to 100), listOf("Song D" to 100))
        val k = 2
        Assertions.assertEquals(2, task.findMostListenedAcrossGenres(genres, k).size)
    }
}