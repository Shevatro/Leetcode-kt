package bctci.dp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.min

class Task40_2 {
    fun calMinivanRoadTrip(detours: IntArray, k: Int): Int {
        return Solution(detours, k).calMinivanRoadTrip()
    }

    private class Solution(
        private val detours: IntArray,
        private val k: Int
    ) {
        private val memo = IntArray(detours.size)

        fun calMinivanRoadTrip(): Int {
            if (detours.size <= k) return 0
            var min = dp(0)
            for (i in 1..k) {
                min = min(min, dp(i))
            }
            return min
        }

        private fun dp(i: Int): Int {
            if (memo[i] != 0) return memo[i]
            if (detours.lastIndex - i <= k) return detours[i]
            var min = dp(i + 1)
            for (amountToSkip in 2..k + 1) {
                min = min(min, dp(i + amountToSkip))
            }
            memo[i] = detours[i] + min
            return memo[i]
        }
    }
}


private class Task40_2Test {
    private val task = Task40_2()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, expected: Int) {
        Assertions.assertEquals(expected, task.calMinivanRoadTrip(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(8, 1, 2, 3, 9, 6, 2, 4), 2, 6),
                Arguments.of(intArrayOf(8, 1, 2, 3, 9, 6, 2, 4), 3, 4),
                Arguments.of(intArrayOf(10, 10), 2, 0),
                Arguments.of(intArrayOf(10, 10), 1, 10),
                Arguments.of(intArrayOf(10), 2, 0),
                Arguments.of(intArrayOf(), 2, 0),
                Arguments.of(intArrayOf(5, 5, 5, 5, 5), 2, 5)
            )
        }
    }
}