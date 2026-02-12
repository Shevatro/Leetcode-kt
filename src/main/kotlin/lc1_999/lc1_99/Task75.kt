package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//This is a Dutch National Flag problem, so it has a one-path solution
//https://leetcode.com/problems/remove-duplicates-from-sorted-array/
class Task75 {
    fun sortColors(nums: IntArray): Unit {
        return Solution(nums).sortColors()
    }

    private class Solution(
        private val nums: IntArray
    ) {
        private val frequencies = IntArray(3)
        fun sortColors() {
            countFrequencies()
            fillColorsByFrequencies()
        }

        private fun countFrequencies() {
            for (num in nums) {
                frequencies[num]++
            }
        }

        private fun findFirstAvailableFrequencyInd(): Int {
            for (i in frequencies.indices) {
                if (frequencies[i] > 0) return i
            }
            throw IllegalStateException("Frequencies are not available")
        }

        private fun fillColorsByFrequencies() {
            var freqInd = findFirstAvailableFrequencyInd()
            for (i in nums.indices) {
                //if we run out of the current color => find the next available
                if (frequencies[freqInd] <= 0) {
                    freqInd = findFirstAvailableFrequencyInd()
                }
                nums[i] = freqInd
                frequencies[freqInd]--
            }
        }
    }
}

private class Task75Test {
    private val task = Task75()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: IntArray) {
        task.sortColors(input)
        Assertions.assertArrayEquals(input, expected)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(2, 0, 2, 1, 1, 0), intArrayOf(0, 0, 1, 1, 2, 2)),
                Arguments.of(intArrayOf(2, 0, 1), intArrayOf(0, 1, 2)),
                Arguments.of(intArrayOf(2), intArrayOf(2))
            )
        }
    }
}

