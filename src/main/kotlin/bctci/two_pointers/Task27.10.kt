package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_10 {

    fun findMissingNumbersInRange(arr: IntArray, low: Int, high: Int): List<Int> {
        val result = mutableListOf<Int>()
        var curNum = low
        var pos = 0
        while (curNum <= high) {
            //out of range -> just add the rest
            if (pos >= arr.size) {
                result.add(curNum)
                curNum++
            //we are not in a range yet -> fast arr forward
            } else if (arr[pos] < curNum) {
                pos++
            //ignore matches
            } else if (arr[pos] == curNum) {
                pos++
                curNum++
            } else {
                result.add(curNum)
                curNum++
            }
        }
        return result
    }
}


private class Task27_10Test {
    private val task = Task27_10()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, input3: Int, expected: List<Int>) {
        Assertions.assertEquals(expected, task.findMissingNumbersInRange(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(6, 9, 12, 15, 18), 9, 13, listOf(10, 11, 13)),
                Arguments.of(intArrayOf(), 9, 9, listOf(9)),
                Arguments.of(intArrayOf(6, 7, 8, 9), 7, 8, emptyList<Int>()),
                Arguments.of(intArrayOf(), 1, 5, listOf(1, 2, 3, 4, 5)),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5), 1, 5, emptyList<Int>()),
                Arguments.of(intArrayOf(1, 3, 5), 1, 5, listOf(2, 4)),
                Arguments.of(intArrayOf(1), 1, 1, emptyList<Int>()),
                Arguments.of(intArrayOf(2), 1, 3, listOf(1, 3))
            )
        }
    }
}