package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/combination-sum-iv
class Task377 {
    fun combinationSum4(nums: IntArray, target: Int): Int {
        return Solution(nums, target).combinationSum4()
    }

    private class Solution(
        private val nums: IntArray,
        private val target: Int
    ) {
        private val memo = IntArray(target) { -1 }
        fun combinationSum4(): Int {
            return dp(0)
        }

        private fun dp(sum: Int): Int {
            if (sum == target) return 1
            if (sum > target) return 0
            if (memo[sum] != -1) return memo[sum]
            var curSum = 0
            for (num in nums) {
                curSum += dp(sum + num)
            }
            memo[sum] = curSum
            return curSum
        }
    }
}

private class Task377Test {
    private val task = Task377()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, expected: Int) {
        Assertions.assertEquals(expected, task.combinationSum4(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 3), 4, 7),
                Arguments.of(intArrayOf(9), 3, 0),
                Arguments.of(
                    intArrayOf(
                        10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210,
                        220, 230, 240, 250, 260, 270, 280, 290, 300, 310, 320, 330, 340, 350, 360, 370, 380, 390, 400,
                        410, 420, 430, 440, 450, 460, 470, 480, 490, 500, 510, 520, 530, 540, 550, 560, 570, 580, 590,
                        600, 610, 620, 630, 640, 650, 660, 670, 680, 690, 700, 710, 720, 730, 740, 750, 760, 770, 780,
                        790, 800, 810, 820, 830, 840, 850, 860, 870, 880, 890, 900, 910, 920, 930, 940, 950, 960, 970, 980, 990, 111
                    ), 999, 1
                )
            )
        }
    }
}