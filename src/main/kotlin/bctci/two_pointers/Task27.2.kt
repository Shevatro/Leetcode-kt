package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_2 {
    fun isPrefixesSmaller(arr: IntArray): Boolean {
        var slowSum = 0
        var fastSum = 0
        var fastP = 0
        for (slowP in 0 until arr.size / 2) {
            slowSum += arr[slowP]
            fastSum += arr[fastP] + arr[fastP + 1]
            fastP += 2
            if (slowSum >= fastSum) return false
        }
        return true
    }
}


private class Task27_2Test {
    private val task = Task27_2()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Boolean) {
        Assertions.assertEquals(expected, task.isPrefixesSmaller(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 2, -1), true),
                Arguments.of(intArrayOf(1, 2, -2, 1, 3, 5), false),
                Arguments.of(intArrayOf(0, 3, 7, 12, 10, 5, 0, 1), true),
                Arguments.of(intArrayOf(), true),
                Arguments.of(intArrayOf(1, 2), true),
                Arguments.of(intArrayOf(2, 1), true),
                Arguments.of(intArrayOf(-2, 1, -4, 5, -3, 7), true),
                Arguments.of(intArrayOf(-2, 1, -14, 8, -3, 2), false)
            )
        }
    }
}