package bctci.dp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.min

class Task40_6 {
    fun minStepsToOne(n: Int): Int {
        return Solution(n).minStepsToOne()
    }

    private class Solution(
        private val n: Int
    ) {
        private val memo = Array(n + 1) { -1 }

        fun minStepsToOne(): Int {
            return dp(n)
        }

        private fun dp(num: Int): Int {
            if (num == 1) return 0
            if (memo[num] != -1) return memo[num]
            var min = dp(num - 1)
            if (num % 3 == 0) min = min(dp(num / 3), min)
            if (num % 2 == 0) min = min(dp(num / 2), min)
            memo[num] = min + 1
            return memo[num]
        }
    }
}


private class Task40_6Test {
    private val task = Task40_6()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Int, expected: Int) {
        Assertions.assertEquals(expected, task.minStepsToOne(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(10, 3),
                Arguments.of(1, 0),
                Arguments.of(15, 4),
                Arguments.of(6, 2),
                Arguments.of(7, 3),
                Arguments.of(100, 7)
            )
        }
    }
}