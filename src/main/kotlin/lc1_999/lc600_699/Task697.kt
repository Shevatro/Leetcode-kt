package lc1_999.lc600_699

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

//Solved
//https://leetcode.com/problems/degree-of-an-array/
class Task697 {
    fun findShortestSubArray(nums: IntArray): Int {
        return Solution(nums).findShortestSubArray()
    }

    private class Solution(private val nums: IntArray) {
        private val maxItems = mutableListOf<Int>()
        fun findShortestSubArray(): Int {
            fillOutMaxItems()
            return calculateMinLength()
        }

        private fun fillOutMaxItems() {
            val frequency = IntArray(50000)
            var maxFrequency = 0
            for (num in nums) {
                frequency[num]++
                maxFrequency = max(maxFrequency, frequency[num])
            }

            for (i in frequency.indices) {
                if (frequency[i] == maxFrequency) {
                    maxItems.add(i)
                }
            }
        }

        private fun calculateMinLength(): Int {
            var minLength = Int.MAX_VALUE
            for (item in maxItems) {
                var startPos = -1
                var endPos = -1
                for (i in nums.indices) {
                    val num = nums[i]
                    if (item == num) {
                        if (startPos == -1) {
                            startPos = i
                        }
                        endPos = i
                    }
                }
                minLength = min(minLength, endPos - startPos + 1)
            }
            return minLength
        }
    }
}

private class Task697Test {
    private val task = Task697()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.findShortestSubArray(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 2, 3, 1), 2),
                Arguments.of(intArrayOf(1, 2, 2, 3, 1, 4, 2), 6),
                Arguments.of(intArrayOf(1, 2), 1),
                Arguments.of(intArrayOf(1), 1),
                Arguments.of(intArrayOf(0), 1)
            )
        }
    }
}