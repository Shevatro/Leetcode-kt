package lc1_999.lc900_999

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//From Beyond Cracking The Coding Interview, From a learning section, Solved
//https://leetcode.com/problems/sort-array-by-parity/
class Task905 {
    fun sortArrayByParity(nums: IntArray): IntArray {
        var startPos = 0
        var endPos = nums.lastIndex
        while (startPos <= endPos) {
            val isStarOdd = nums[startPos] % 2 != 0
            val isEndEven = nums[endPos] % 2 == 0
            if (isStarOdd && isEndEven) {
                val temp = nums[startPos]
                nums[startPos] = nums[endPos]
                nums[endPos] = temp
                startPos++
                endPos--
            } else {
                if (!isStarOdd) startPos++
                if (!isEndEven) endPos--
            }
        }
        return nums
    }
}

private class Task905Test {
    private val task = Task905()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.sortArrayByParity(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(0), intArrayOf(0)),
                Arguments.of(intArrayOf(3, 1, 5, 7), intArrayOf(3, 1, 5, 7)),
                Arguments.of(intArrayOf(2, 4, 8, 10), intArrayOf(2, 4, 8, 10)),
                Arguments.of(intArrayOf(7, 4, 5, 7), intArrayOf(4, 7, 5, 7)),
                Arguments.of(intArrayOf(7, 6, 4), intArrayOf(4, 6, 7))
            )
        }
    }
}

