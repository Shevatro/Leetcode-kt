package lc1_999.lc500_599

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/array-partition/description/
class Task561 {
    fun arrayPairSum(nums: IntArray): Int {
        nums.sort()
        var sum = 0
        for (i in 0 until nums.size step 2) {
            //the smallest of 2 items (i, i+1) is always smaller
            sum += nums[i]
        }
        return sum
    }
}

private class Task561Test {
    private val task = Task561()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.arrayPairSum(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 4, 3, 2), 4),
                Arguments.of(intArrayOf(6, 2, 6, 5, 1, 2), 9)
            )
        }
    }
}