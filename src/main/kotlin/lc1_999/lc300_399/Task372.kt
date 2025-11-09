package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigInteger
import java.util.stream.Stream

//Solved but not optimal
//https://leetcode.com/problems/super-pow/
class Task372() {
    private val MOD = 1337.toBigInteger()
    fun superPow(a: Int, b: IntArray): Int {
        val bNumber = arrayToNumber(b)
        return powRec(a.toBigInteger(), bNumber).toInt()
    }

    private fun arrayToNumber(arr: IntArray): BigInteger {
        var sum = 0.toBigInteger()
        var pow = 1.toBigInteger()
        for (i in arr.size - 1 downTo 0) {
            sum += arr[i].toBigInteger() * pow
            pow *= 10.toBigInteger()
        }
        return sum
    }

    private fun powRec(a: BigInteger, b: BigInteger): BigInteger {
        if (b == 0.toBigInteger()) return 1.toBigInteger()
        val result = if (b % 2.toBigInteger() == 0.toBigInteger()) {
            val half = powRec(a, b / 2.toBigInteger()) % MOD
            half * half
        } else {
            a * powRec(a, b - 1.toBigInteger()) % MOD
        }
        return result % MOD
    }
}

private class Task372Test {
    private val task = Task372()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Int, input2: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.superPow(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(2, intArrayOf(3), 8),
                Arguments.of(2, intArrayOf(1, 0), 1024),
                Arguments.of(1, intArrayOf(4, 3, 3, 8, 5, 2), 1)
            )
        }
    }
}