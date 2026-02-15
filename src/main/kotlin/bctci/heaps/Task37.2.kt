package bctci.heaps

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.PriorityQueue
import java.util.stream.Stream

class Task37_2 {

    fun findKMostPlayed(songs: List<Pair<String, Int>>, k: Int): List<String> {
        return Solution(songs, k).findKMostPlayed()
    }

    private class Solution(
        private val songs: List<Pair<String, Int>>,
        private val k: Int
    ) {
        private val heap = PriorityQueue<Pair<String, Int>>(compareBy { it.second })
        fun findKMostPlayed(): List<String> {
            fillHeap()
            return generateResult()
        }

        private fun fillHeap() {
            for (song in songs) {
                heap.add(song)
                //keep only K items in a heap, so we don't care about the smallest (top)
                if (heap.size > k) heap.poll()
            }
        }

        private fun generateResult(): List<String> {
            val result = mutableListOf<String>()
            var limit = k
            while (heap.isNotEmpty() && limit > 0) {
                result.add(heap.poll().first)
                limit--
            }
            return result

        }
    }
}


private class Task37_2Test {
    private val task = Task37_2()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: List<Pair<String, Int>>, input2: Int, expected: List<String>) {
        Assertions.assertEquals(expected.sorted(), task.findKMostPlayed(input1, input2).sorted())
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(
                        "All the Single Brackets" to 132, "Oops! I Broke Prod Again" to 274,
                        "Coding In The Deep" to 146, "Boolean Rhapsody" to 193, "Here Comes The Bug" to 291,
                        "All About That Base Case" to 291
                    ), 3,
                    listOf("All About That Base Case", "Here Comes The Bug", "Oops! I Broke Prod Again")
                ),
                // Test with fewer songs than k
                Arguments.of(listOf("Song A" to 100, "Song B" to 200), 5, listOf("Song A", "Song B")),
                // Test with exact k songs
                Arguments.of(listOf("Song A" to 100, "Song B" to 200, "Song C" to 300), 3, listOf("Song A", "Song B", "Song C")),
                // Test with k = 1
                Arguments.of(listOf("Song A" to 100, "Song B" to 200, "Song C" to 300), 1, listOf("Song C")),
                // Test with ties in play counts
                Arguments.of(listOf("Song A" to 100, "Song B" to 100, "Song C" to 200, "Song D" to 200), 2, listOf("Song C", "Song D")),
                // Test empty input
                Arguments.of(emptyList<Pair<String, Int>>(), 3, emptyList<String>())
            )
        }
    }
}