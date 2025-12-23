package bctci.dp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task40_1 {
    fun calRoadTrip(detours: IntArray): Int {
        return SolutionTabulation(detours).calRoadTrip()
    }

    private class SolutionForwardRecursion(
        private val detours: IntArray
    ) {
        private val memo = IntArray(detours.size)

        fun calRoadTrip(): Int {
            if (detours.size <= 2) return 0
            return minOf(dp(0), dp(1), dp(2))
        }

        private fun dp(i: Int): Int {
            if (memo[i] != 0) return memo[i]
            if (i in memo.lastIndex - 2..memo.lastIndex) return detours[i]
            memo[i] = detours[i] + minOf(dp(i + 1), dp(i + 2), dp(i + 3))
            return memo[i]
        }
    }

    private class SolutionBackwardRecursion(
        private val detours: IntArray
    ) {
        private val memo = IntArray(detours.size)

        fun calRoadTrip(): Int {
            if (detours.size <= 2) return 0
            return minOf(dp(memo.lastIndex), dp(memo.lastIndex - 1), dp(memo.lastIndex - 2))
        }

        private fun dp(i: Int): Int {
            if (memo[i] != 0) return memo[i]
            if (i in 0..2) return detours[i]
            memo[i] = detours[i] + minOf(dp(i - 1), dp(i - 2), dp(i - 3))
            return memo[i]
        }
    }

    //bottom up
    private class SolutionTabulation(
        private val detours: IntArray
    ) {

        fun calRoadTrip(): Int {
            if (detours.size <= 2) return 0
            var `i-1` = detours[2]
            var `i-2` = detours[1]
            var `i-3` = detours[0]
            for (i in 3 until detours.size) {
                val n = detours[i] + minOf(`i-3`, `i-2`, `i-1`)
                `i-3` = `i-2`
                `i-2` = `i-1`
                `i-1` = n
            }
            return minOf(`i-1`, `i-2`, `i-3`)
        }
    }
}


private class Task40_1Test {
    private val task = Task40_1()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.calRoadTrip(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(8, 1, 2, 3, 9, 6, 2, 4), 6),
                Arguments.of(intArrayOf(8, 1, 2, 3, 9, 3, 2, 4), 5),
                Arguments.of(intArrayOf(10, 10), 0),
                Arguments.of(intArrayOf(10), 0),
                Arguments.of(intArrayOf(), 0),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 12),
                Arguments.of(intArrayOf(5, 5, 5, 5, 5, 5, 5, 5, 5), 15),
                Arguments.of(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1), 3),
                Arguments.of(intArrayOf(1, 2, 3), 1),
                Arguments.of(intArrayOf(1, 2, 3, 4), 2),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5), 3),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), 5)
            )
        }
    }
}