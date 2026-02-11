package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/first-missing-positive/description/
class Task41 {
    fun firstMissingPositive(nums: IntArray): Int {
        var k = 1
        while (k <= nums.size) {
            val index = nums[k - 1]
            //check if we didn't process it before and it's in a range [1, size]
            if (index != k && index > 0 && index <= nums.size) {
                val temp = nums[index - 1]
                nums[index - 1] = index
                //do not swap if the next one is incorrect
                if (temp != index && temp > 0 && temp <= nums.size) {
                    nums[k - 1] = temp
                } else {
                    k++
                }
            } else {
                k++
            }
        }
        for (i in nums.indices) {
            if (i + 1 != nums[i]) return i + 1
        }
        return nums.size + 1
    }

    fun firstMissingPositiveSimplified(nums: IntArray): Int {
        val numsSet = nums.toSet()
        for (num in 1..Int.MAX_VALUE) {
            if (!numsSet.contains(num)) {
                return num
            }
        }
        return -1
    }
}

private class Task41Test {
    private val task = Task41()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.firstMissingPositive(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 0), 3),
                Arguments.of(intArrayOf(3, 4, -1, 1), 2),
                Arguments.of(intArrayOf(7, 8, 9, 11, 12), 1),
                Arguments.of(intArrayOf(1), 2),
                Arguments.of(intArrayOf(0), 1),
                Arguments.of(intArrayOf(-10), 1),
                Arguments.of(intArrayOf(100000, 3, 4000, 2, 15, 1, 99999), 4),
                Arguments.of(intArrayOf(2, 2), 1),
                Arguments.of(intArrayOf(0, 1, 2), 3),
                Arguments.of(intArrayOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 1), 25)
            )
        }
    }
}