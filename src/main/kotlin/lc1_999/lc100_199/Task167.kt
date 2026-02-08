package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
class Task167 {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var startPos = 0
        var endPos = numbers.lastIndex
        while (startPos < endPos) {
            val sum = numbers[startPos] + numbers[endPos]
            if (sum == target) return intArrayOf(startPos + 1, endPos + 1)
            if (sum > target) {
                endPos--
            } else {
                startPos++
            }
        }
        throw IllegalArgumentException("Invalid test case")
    }

    fun twoSumUsingBSearch(numbers: IntArray, target: Int): IntArray {
        for (i in numbers.indices) {
            val leftOver = target - numbers[i]
            val result = numbers.binarySearch(leftOver, i + 1)
            if (result >= 0) return intArrayOf(i + 1, result + 1)
        }
        throw IllegalArgumentException("Invalid test case")
    }
}

private class Task167Test {
    private val task = Task167()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.twoSum(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(2, 7, 11, 15), 9, intArrayOf(1, 2)),
                Arguments.of(intArrayOf(2, 3, 4), 6, intArrayOf(1, 3)),
                Arguments.of(intArrayOf(-1, 0), -1, intArrayOf(1, 2)),
                Arguments.of(intArrayOf(1, 2, 3, 4, 4, 9, 56, 90), 8, intArrayOf(4, 5))
            )
        }
    }
}