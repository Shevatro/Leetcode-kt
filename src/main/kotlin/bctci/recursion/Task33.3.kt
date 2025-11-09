package bctci.recursion

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task33_3() {

    fun calculatePowersModM(a: Long, p: Long, m: Long): Long {
        if (p == 0L) return 1
        if (p == 1L) return a % m
        val result = if (p % 2L == 0L) {
            val halfP = calculatePowersModM(a, p / 2, m) % m
            halfP * halfP
        } else {
            a * calculatePowersModM(a, p - 1, m) % m
        }
        return result % m
    }

    fun calculatePowersModMIter(a: Long, p: Long, m: Long): Long {
        if (p == 0L) return 1
        var sum = 1L
        var curA = a
        var curP = p
        while (curP > 0) {
            if (curP % 2 == 1L) {
                sum = (sum * curA) % m
                curP--
            } else {
                curA = (curA * curA) % m
                curP /= 2
            }
        }
        return sum % m
    }
}


private class Task33_3Test {
    private val task = Task33_3()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Long, input2: Long, input3: Long, expected: Long) {
        Assertions.assertEquals(expected, task.calculatePowersModMIter(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(2, 5, 100, 32),
                Arguments.of(2, 5, 30, 2),
                Arguments.of(123456789, 987654321, 1000000007, 652541198),
                Arguments.of(3, 1, 5, 3),
                Arguments.of(5, 3, 7, 6),
                Arguments.of(2, 0, 10, 1)
            )
        }
    }
}