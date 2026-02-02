package lc2000_2999.lc2700_2799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/split-strings-by-separator/description/
class Task2788 {
    fun splitWordsBySeparator(words: List<String>, separator: Char): List<String> {
        val result = mutableListOf<String>()
        for (word in words) {
            val curList = split(word, separator)
            if (curList.isNotEmpty()) result.addAll(curList)
        }
        return result
    }

    private fun split(str: String, separator: Char): List<String> {
        val result = mutableListOf<String>()
        var sb = StringBuilder()
        for (ch in str) {
            if (ch == separator) {
                val curStr = sb.toString()
                if (curStr.isNotEmpty()) {
                    result.add(curStr)
                    sb = StringBuilder()
                }
            } else {
                sb.append(ch)
            }
        }
        val leftOver = sb.toString()
        if (leftOver.isNotEmpty()) result.add(leftOver)
        return result
    }
}

private class Task2788Test {
    private val task = Task2788()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: List<String>, input2: Char, expected: List<String>) {
        Assertions.assertEquals(expected, task.splitWordsBySeparator(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf("one.two.three", "four.five", "six"), '.',
                    listOf("one", "two", "three", "four", "five", "six")
                ),
                Arguments.of(listOf("\$easy$", "\$problem$"), '$', listOf("easy", "problem")),
                Arguments.of(listOf("|||"), '|', emptyList<String>())
            )
        }
    }
}