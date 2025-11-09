package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.abs

//From Beyond Cracking The Coding Interview, Solved with a hint
//https://leetcode.com/problems/powx-n/
class Task50() {
    fun myPow(x: Double, n: Int): Double {
        val newX = if (n<0) 1/x else x
        return powRec(newX, abs(n.toLong()))
    }
    private fun powRec(x: Double, n: Long): Double{
        if (n == 0L) return 1.0
        return if (n % 2 == 0L){
            val half = powRec(x, n/2)
            half * half
        }else{
            x *  powRec(x, n-1)
        }
    }
}

private class Task50Test {
    private val task = Task50()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Double, input2: Int, expected: Double) {
        val delta = 0.000001
        Assertions.assertEquals(expected, task.myPow(input1, input2), delta)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(2.0, 10, 1024.0),
                Arguments.of(2.1, 3, 9.261),
                Arguments.of(2.0, -2, 0.25),
                Arguments.of(0.44528, 0, 1.0),
                Arguments.of(2.0,-2147483648, 0.0)
            )
        }
    }
}