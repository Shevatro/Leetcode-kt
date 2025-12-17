package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task39_6 {
    fun getJumpingNumbers(n: Int): List<Int> {
        return Solution(n).getJumpingNumbers()
    }

    private class Solution(private val n: Int) {
        private val result = mutableListOf<Int>()

        fun getJumpingNumbers(): List<Int> {
            bfs()
            return result
        }

        private fun bfs() {
            val queue = initQueue()
            while (!queue.isEmpty()) {
                val current = queue.removeFirst()
                if (current < n) result.add(current)
                repeat(2) { isEven ->
                    val diff = if (isEven % 2 == 0) -1 else 1
                    val newDigit = current % 10 + diff
                    if (newDigit in 0..9) {
                        val newNumber = current * 10 + newDigit
                        if (newNumber < n) queue.addLast(newNumber)
                    }
                }
            }
        }

        private fun initQueue(): ArrayDeque<Int> {
            val queue = ArrayDeque<Int>()
            val initialNum = if (n >= 9) 9 else n - 1
            for (i in 1..initialNum) {
                queue.addLast(i)
            }
            return queue
        }
    }
}


private class Task39_6Test {
    private val task = Task39_6()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Int, expected: List<Int>) {
        println(expected.size.toString())
        Assertions.assertEquals(expected, task.getJumpingNumbers(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(34, listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21, 23, 32)),
                Arguments.of(1, emptyList<Int>()),
                Arguments.of(5, listOf(1, 2, 3, 4)),
                Arguments.of(10, listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)),
                Arguments.of(50, listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21, 23, 32, 34, 43, 45)),
                Arguments.of(
                    102,
                    listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98, 101)
                ),
                Arguments.of(
                    1000,
                    listOf(
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98, 101,
                        121, 123, 210, 212, 232, 234, 321, 323, 343, 345, 432, 434, 454, 456, 543, 545, 565, 567, 654,
                        656, 676, 678, 765, 767, 787, 789, 876, 878, 898, 987, 989
                    )
                )
            )
        }
    }
}