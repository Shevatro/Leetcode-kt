package lc1_999.lc700_799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.PriorityQueue
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/reorganize-string/
class Task767 {
    fun reorganizeString(s: String): String {
        val freq = IntArray(26)
        for (ch in s) {
            val code = ch - 'a'
            freq[code]++
        }

        val maxHeap = PriorityQueue<Int>(compareByDescending { freq[it] })
        for (i in freq.indices) {
            if (freq[i] != 0) maxHeap.add(i)
        }
        var previous = -1
        val sb = StringBuilder()
        while (maxHeap.isNotEmpty()) {
            val top = maxHeap.poll()
            if (top != previous) {
                sb.append((top + 'a'.code).toChar())
                freq[top]--
                previous = top
                if (freq[top] != 0) maxHeap.add(top)
            } else if (maxHeap.isNotEmpty()) {
                val stop = maxHeap.poll()
                sb.append((stop + 'a'.code).toChar())
                freq[stop]--
                previous = stop
                if (freq[stop] != 0) maxHeap.add(stop)
                maxHeap.add(top)
            } else {
                return ""
            }
        }
        return sb.toString()
    }
}

private class Task767Test {
    private val task = Task767()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: String) {
        if (expected.length <= 1) {
            Assertions.assertEquals(expected, task.reorganizeString(input))
        } else {
            Assertions.assertEquals(true, isValid(task.reorganizeString(input)))
        }
    }

    private fun isValid(str: String): Boolean {
        var previous = str[0]
        for (i in 1 until str.length) {
            if (previous == str[i]) return false
            previous = str[i]
        }
        return true
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("aab", "aba"),
                Arguments.of("aaab", ""),
                Arguments.of("waabckwxbsvdcbhsbxfmzbnzvaaaaa", "abababawazasvbxcxcwhkfnmvszdba"),
                Arguments.of("j", "j")
            )
        }
    }
}