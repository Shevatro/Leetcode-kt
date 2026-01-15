package lc2000_2999.lc2000_2099

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// Solved but with a hint
//https://leetcode.com/problems/convert-1d-array-into-2d-array/
class Task2022 {
    fun construct2DArray(original: IntArray, m: Int, n: Int): Array<IntArray> {
        if (original.size != m * n) return emptyArray<IntArray>()
        val result = Array(m) { IntArray(n) }
        for (i in original.indices) {
            val r = i / n
            val c = i % n
            result[r][c] = original[i]
        }
        return result
    }
}

private class Task2022Test {
    private val task = Task2022()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, input3: Int, expected: Array<IntArray>) {
        Assertions.assertArrayEquals(expected, task.construct2DArray(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 3, 4), 2, 2, arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))),
                Arguments.of(intArrayOf(1, 2, 3), 1, 3, arrayOf(intArrayOf(1, 2, 3))),
                Arguments.of(intArrayOf(1, 2), 1, 1, emptyArray<IntArray>()),
                Arguments.of(intArrayOf(1, 2, 3), 3, 1, arrayOf(intArrayOf(1), intArrayOf(2), intArrayOf(3)))
            )
        }
    }
}