package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved with a hint
//https://leetcode.com/problems/single-number/
class Task136 {
    fun singleNumber(nums: IntArray): Int {
        var result = nums[0]
        for (i in 1 until nums.size) {
            result = result xor nums[i]
        }
        return result
    }
}

private class Task136Test {
    private val task = Task136()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.singleNumber(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(2, 2, 1), 1),
                Arguments.of(intArrayOf(4, 1, 2, 1, 2), 4),
                Arguments.of(intArrayOf(1), 1)
            )
        }
    }
}