package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_13 {

    fun sortArrayByParity(arr: IntArray) {
        var startPos = 0
        var endPos = arr.lastIndex
        while (startPos < endPos) {
            val evenItem = arr[startPos]
            if (evenItem % 2 == 0) {
                startPos++
                continue
            }
            val oddItem = arr[endPos]
            if (oddItem % 2 == 1) {
                endPos--
                continue
            }
            val temp = arr[startPos]
            arr[startPos] = arr[endPos]
            arr[endPos] = temp
            startPos++
            endPos--
        }
    }
}


private class Task27_13Test {
    private val task = Task27_13()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: List<IntArray>) {
        task.sortArrayByParity(input)
        val isActualCorrect = input.toList() in expected.map { it.toList() }
        Assertions.assertEquals(true, isActualCorrect)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    intArrayOf(1, 2, 3, 4, 5),
                    listOf(intArrayOf(2, 4, 1, 3, 5), intArrayOf(4, 2, 3, 1, 5))
                ),
                Arguments.of(intArrayOf(5, 1, 3, 1, 5), listOf(intArrayOf(5, 1, 3, 1, 5))),
                Arguments.of(intArrayOf(), listOf(intArrayOf())),
                Arguments.of(intArrayOf(1), listOf(intArrayOf(1))),
                Arguments.of(intArrayOf(2), listOf(intArrayOf(2))),
                Arguments.of(intArrayOf(1, 2), listOf(intArrayOf(2, 1))),
                Arguments.of(intArrayOf(2, 1), listOf(intArrayOf(2, 1))),
                Arguments.of(
                    intArrayOf(1, 3, 2, 4),
                    listOf(intArrayOf(2, 4, 1, 3), intArrayOf(4, 2, 3, 1))
                )
            )
        }
    }
}