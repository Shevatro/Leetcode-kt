package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Black list, Solved
//Note: It's on a black list because it allows ONLY this solution, other approaches will fail due to strict test cases
//https://leetcode.com/problems/missing-ranges/
class Task163 {
    fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        var startInterv = lower
        for (num in nums) {
            if (startInterv == num) {
                startInterv++
            } else if (startInterv < num) {
                result.add(listOf(startInterv, num - 1))
                startInterv = num + 1
            }
        }
        if (startInterv <= upper) {
            result.add(listOf(startInterv, upper))
        }
        return result
    }
}

private class Task163Test {
    private val task = Task163()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, input3: Int, expected: List<List<Int>>) {
        Assertions.assertEquals(expected, task.findMissingRanges(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    intArrayOf(0, 1, 3, 50, 75), 0, 99,
                    listOf(listOf(2, 2), listOf(4, 49), listOf(51, 74), listOf(76, 99))
                ),
                Arguments.of(intArrayOf(-1), -1, -1, emptyList<List<Int>>()),
                Arguments.of(
                    intArrayOf(0, 1, 3, 50, 75), 0, 75,
                    listOf(listOf(2, 2), listOf(4, 49), listOf(51, 74))
                ),
                Arguments.of(intArrayOf(), 15, 75, listOf(listOf(15, 75))),
                Arguments.of(
                    intArrayOf(-1000000000, 1000000000), -1000000000, 1000000000,
                    listOf(listOf(-999999999, 999999999))
                ),
                Arguments.of(intArrayOf(), 1, 1, listOf(listOf(1, 1))),
                Arguments.of(intArrayOf(-1), -1, 0, listOf(listOf(0, 0))),
                Arguments.of(intArrayOf(1000000000), 0, 1000000000, listOf(listOf(0, 999999999)))
            )
        }
    }
}