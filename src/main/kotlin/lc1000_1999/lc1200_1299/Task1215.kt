package lc1000_1999.lc1200_1299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/stepping-numbers/
class Task1215 {
    fun countSteppingNumbers(low: Int, high: Int): List<Int> {
        return Solution2(low, high).countSteppingNumbers()
    }

    private class Solution(
        private val low: Int,
        private val high: Int
    ) {
        private val result = mutableListOf<Int>()

        fun countSteppingNumbers(): List<Int> {
            if (low == 0) result.add(0)
            bfs()
            return result.filter { it in low..high }
        }

        private fun bfs() {
            val queue = initQueue()
            while (!queue.isEmpty()) {
                val current = queue.removeFirst()
                if (current <= high) result.add(current)
                if (current == 0) continue
                repeat(2) { isEven ->
                    val diff = if (isEven % 2 == 0) -1 else 1
                    val newDigit = current % 10 + diff
                    if (newDigit in 0..9) {
                        val newNumber = current * 10L + newDigit
                        if (newNumber <= high) queue.addLast(newNumber.toInt())
                    }
                }
            }
        }

        private fun initQueue(): ArrayDeque<Int> {
            val queue = ArrayDeque<Int>()
            for (i in 1..9) {
                queue.addLast(i)
            }
            return queue
        }
    }

    private class Solution2(
        private val low: Int,
        private val high: Int
    ) {
        private val result = mutableListOf<Int>()

        fun countSteppingNumbers(): List<Int> {
            if (low == 0) result.add(0)
            for (i in 1..9) {
                backtrack(i)
            }
            return result.filter { it in low..high }.sorted()
        }

        private fun backtrack(num: Int) {
            if (num > high) return
            result.add(num)
            val previousDigit = num % 10
            repeat(2) { isEven ->
                val diff = if (isEven % 2 == 0) -1 else 1
                val newDigit = previousDigit + diff
                if (newDigit in 0..9) {
                    //prevent overflow
                    if (num * 10L <= high) backtrack(num * 10 + newDigit)
                }
            }
        }
    }
}

private class Task1215Test {
    private val task = Task1215()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Int, input2: Int, expected: List<Int>) {
        Assertions.assertEquals(expected, task.countSteppingNumbers(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(0, 21, listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21)),
                Arguments.of(10, 15, listOf(10, 12)),
                Arguments.of(200, 300, listOf(210, 212, 232, 234))
            )
        }
    }
}