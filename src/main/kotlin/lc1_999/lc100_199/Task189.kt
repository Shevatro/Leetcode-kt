package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/rotate-array/
class Task189 {
    fun rotate(nums: IntArray, k: Int): Unit {
        val repeatTime = if (k <= nums.size) k else k % nums.size
        val numsCopy = nums.clone()
        var readPos = nums.size - repeatTime
        for (writePos in nums.indices) {
            if (readPos == nums.size) readPos = 0
            nums[writePos] = numsCopy[readPos]
            readPos++
        }
    }
}

private class Task189Test {
    private val task = Task189()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, expected: IntArray) {
        task.rotate(input1, input2)
        Assertions.assertArrayEquals(input1, expected)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7), 3, intArrayOf(5, 6, 7, 1, 2, 3, 4)),
                Arguments.of(intArrayOf(-1, -100, 3, 99), 2, intArrayOf(3, 99, -1, -100)),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), 3, intArrayOf(4, 5, 6, 1, 2, 3)),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), 2, intArrayOf(5, 6, 1, 2, 3, 4)),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), 4, intArrayOf(3, 4, 5, 6, 1, 2)),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), 0, intArrayOf(1, 2, 3, 4, 5, 6)),
                Arguments.of(intArrayOf(-1), 2, intArrayOf(-1)),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), 10, intArrayOf(3, 4, 5, 6, 1, 2))
            )
        }
    }
}

