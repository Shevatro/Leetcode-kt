package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//From Beyond Cracking The Coding Interview, From a learning section, Solved
//https://leetcode.com/problems/remove-duplicates-from-sorted-array/
class Task26 {
    fun removeDuplicates(nums: IntArray): Int {
        var fillPos = 0
        for (i in 1 until nums.size) {
            if (nums[i] != nums[fillPos]) {
                fillPos++
                nums[fillPos] = nums[i]

            }
        }
        return fillPos + 1
    }
}

private class Task26Test {
    private val task = Task26()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.removeDuplicates(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 1, 2), 2),
                Arguments.of(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4), 5),
                Arguments.of(intArrayOf(1), 1),
                Arguments.of(intArrayOf(1, 1, 1, 1), 1),
                Arguments.of(intArrayOf(1, 2, 3, 4), 4)
            )
        }
    }
}

