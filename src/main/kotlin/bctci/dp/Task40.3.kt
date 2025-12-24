package bctci.dp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

class Task40_3 {
    fun maxRestaurantRatings(ratings: DoubleArray): Double {
        return Solution(ratings).maxRestaurantRatings()
    }

    private class Solution(
        private val ratings: DoubleArray
    ) {
        private val memo = DoubleArray(ratings.size) { Double.MIN_VALUE }

        fun maxRestaurantRatings(): Double {
            return dp(0)
        }

        private fun dp(i: Int): Double {
            if (i > ratings.lastIndex) return 0.0
            if (memo[i] != Double.MIN_VALUE) return memo[i]
            memo[i] = max(ratings[i] + dp(i + 2), dp(i + 1))
            return memo[i]
        }
    }
}


private class Task40_3Test {
    private val task = Task40_3()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: DoubleArray, expected: Double) {
        Assertions.assertEquals(expected, task.maxRestaurantRatings(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(doubleArrayOf(8.0, 1.0, 3.0, 9.0, 5.0, 2.0, 1.0), 19.0),
                Arguments.of(doubleArrayOf(8.0, 1.0, 3.0, 7.0, 5.0, 2.0, 4.0), 20.0),
                Arguments.of(doubleArrayOf(), 0.0),
                Arguments.of(doubleArrayOf(10.0, 10.0, 10.0, 10.0, 10.0), 30.0),
                Arguments.of(doubleArrayOf(5.0, 5.0, 5.0, 5.0, 5.0), 15)
            )
        }
    }
}