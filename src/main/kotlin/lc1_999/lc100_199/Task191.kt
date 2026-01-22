package lc1_999.lc100_199

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/number-of-1-bits
class Task191 {
    private val memo = mutableMapOf<Int, Int>()
    fun hammingWeight(n: Int): Int {
        return hammingWeightManyTimesCall(n)
    }

    fun hammingWeightSimplest(n: Int): Int {
        return n.toString(2).count { it == '1' }
    }

    fun hammingWeightOptimal(n: Int): Int {
        var amount = 0
        var curNum = n
        while (curNum != 0) {
            if (curNum % 2 == 1) amount++
            curNum /= 2
        }
        return amount
    }

    fun hammingWeightManyTimesCall(n: Int): Int {
        return dp(n)
    }

    private fun dp(num: Int): Int {
        if (num == 0) return 0
        memo[num]?.let { return it }
        memo[num] = dp(num / 2) + num % 2
        return requireNotNull(memo[num])
    }
}

private class Task191Test {
    private val task = Task191()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Int, expected: Int) {
        Assertions.assertEquals(expected, task.hammingWeight(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(11, 3),
                Arguments.of(128, 1),
                Arguments.of(2147483645, 30)
            )
        }
    }
}