package bctci.heaps

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.PriorityQueue
import java.util.stream.Stream

class Task37_7 {

    fun makePlaylist(songs: List<Pair<String, String>>): List<String> {
        return Solution(songs).makePlaylist()
    }

    private class Solution(
        private val songs: List<Pair<String, String>>
    ) {
        private val artistsMap = mutableMapOf<String, MutableSet<String>>()
        private val maxHeap = PriorityQueue<String>(compareByDescending { artistsMap[it]?.size ?: 0 })
        fun makePlaylist(): List<String> {
            fillArtistsMap()
            addArtistToHeap()
            return generatePlaylist()
        }

        private fun fillArtistsMap() {
            for (songAndArtist in songs) {
                val (song, artist) = songAndArtist
                if (artistsMap[artist] == null) {
                    artistsMap[artist] = mutableSetOf()
                }
                artistsMap[artist]?.add(song)
            }
        }

        private fun addArtistToHeap() {
            for (artist in artistsMap.keys) {
                maxHeap.add(artist)
            }
        }

        private fun generatePlaylist(): List<String> {
            val result = mutableListOf<String>()
            var previousArtist = ""
            while (maxHeap.isNotEmpty()) {
                var top = maxHeap.poll()
                if (previousArtist == top) {
                    if (maxHeap.isEmpty()) return emptyList()
                    val secondTop = maxHeap.poll()
                    maxHeap.add(top)
                    top = secondTop
                }
                val item = artistsMap[top]!!.first()
                artistsMap[top]?.remove(item)
                result.add(item)
                if (artistsMap[top]!!.isNotEmpty()) maxHeap.add(top)
                previousArtist = top
            }
            return result
        }

    }
}


private class Task37_7Test {
    private val task = Task37_7()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: List<Pair<String, String>>, expectedEmpty: Boolean) {
        val actual = task.makePlaylist(input)
        if (expectedEmpty) {
            Assertions.assertEquals(0, actual.size)
        } else {
            Assertions.assertEquals(input.size, actual.size)
            val artists = actual.mapNotNull { song -> input.find { song == it.first }?.second }
            Assertions.assertEquals(true, isValid(artists))
        }
    }

    private fun isValid(artists: List<String>): Boolean {
        var previous = artists[0]
        for (i in 1 until artists.size) {
            if (previous == artists[i]) return false
            previous = artists[i]
        }
        return true
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(
                        "Coding In The Deep" to "A Dell", "Hello World" to "A Dell", "Someone Like GNU" to "A Dell",
                        "Make You Read My Logs" to "A Dell", "Hey Queue" to "The Bugs", "Here Comes the Bug" to "The Bugs",
                        "Merge Together" to "The Bugs", "Dirty Data" to "Michael JSON", "Man in the Middle Attack" to "Michael JSON",
                        "Ring Of Firewalls" to "Johnny Cache"
                    ), false
                ),
                // Test with no songs
                Arguments.of(emptyList<Pair<String, String>>(), true),
                // Test with one song
                Arguments.of(listOf("Single Song" to "Solo Artist"), false),
                // Test with two songs by different artists
                Arguments.of(listOf("Song A" to "Artist 1", "Song B" to "Artist 2"), false),
                // Test with two songs by the same artist (impossible)
                Arguments.of(listOf("Song A" to "Artist 1", "Song B" to "Artist 1"), true),
                // Test with more songs by one artist than ceiling(n/2)
                Arguments.of(
                    listOf("Song 1" to "Artist 1", "Song 2" to "Artist 1", "Song 3" to "Artist 1", "Song 4" to "Artist 2"), true
                ),
                // Test with exactly ceiling(n/2) songs by one artist (should work)
                Arguments.of(
                    listOf(
                        "Song 1" to "Artist 1", "Song 2" to "Artist 1", "Song 3" to "Artist 1", "Song 4" to "Artist 2", "Song 5" to "Artist 3"
                    ), false
                )
            )
        }
    }
}