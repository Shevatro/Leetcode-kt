package bctci.dp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task40_1 {
    fun calRoadTrip(detours: IntArray): Int {
        return Solution(detours).calRoadTrip()
    }

    private class Solution(
        private val detours: IntArray
    ) {
        private val memo = IntArray(detours.size) { Int.MIN_VALUE }

        fun calRoadTrip(): Int {
            if (detours.size <= 2) return 0
            return listOf(dp(0), dp(1), dp(2)).min()
        }

        private fun dp(i: Int): Int {
            if (memo[i] != Int.MIN_VALUE) return memo[i]
            if (i in memo.lastIndex - 2..memo.lastIndex) return detours[i]
            memo[i] = detours[i] + listOf(dp(i + 1), dp(i + 2), dp(i + 3)).min()
            return memo[i]
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