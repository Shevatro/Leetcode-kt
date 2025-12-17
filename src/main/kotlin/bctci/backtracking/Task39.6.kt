package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.LinkedList
import java.util.stream.Stream
import kotlin.math.pow

class Task39_6 {
    fun getJumpingNumbers(n: Int): List<Int> {
        return Solution(n).getJumpingNumbers()
    }

    private class Solution(
        private val n: Int
    ) {
        private val digits = convertNToDigits()
        private val result = mutableListOf<Int>()
        private val curNumber = LinkedList<Int>()

        fun getJumpingNumbers(): List<Int> {
            backtrack(0)
            return result.sorted()
        }

        private fun backtrack(i: Int) {
            if (i != 0) {
                val num = curNumber.mapIndexed { index, value -> 10.0.pow(index).toInt() * value }.sum()
                if (num < n) result.add(num)
                if (i == digits.size) return
            }
            val previousDigit = curNumber.peekFirst()
            if (previousDigit == null) {
                for (num in 1..9) {
                    curNumber.addFirst(num)
                    backtrack(i + 1)
                    curNumber.removeFirst()
                }
            } else {
                repeat(2) { times ->
                    val diff = if (times % 2 == 0) -1 else 1
                    val num = previousDigit + diff
                    if (num in 0..9) {
                        curNumber.addFirst(num)
                        backtrack(i + 1)
                        curNumber.removeFirst()
                    }
                }
            }
        }

        private fun convertNToDigits(): List<Int> {
            //TODO improve
            return n.toString().toCharArray().map { it.digitToInt() }
        }
    }
}


private class Task39_6Test {
    private val task = Task39_6()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Int, expected: List<Int>) {
        Assertions.assertEquals(expected, task.getJumpingNumbers(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(34, listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21, 23, 32)),
                Arguments.of(1, emptyList<Int>()),
                Arguments.of(10, listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)),
                Arguments.of(50, listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21, 23, 32, 34, 43, 45)),
                Arguments.of(
                    102,
                    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98, 101)
                )
            )
        }
    }
}