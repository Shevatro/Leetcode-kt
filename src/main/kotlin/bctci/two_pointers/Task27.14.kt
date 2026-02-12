package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_14 {

    fun removeDuplicatesInPlace(arr: IntArray): Int {
        var readPos = 0
        var writePos = 0
        var previous = Int.MIN_VALUE
        //it's also possible to use for loop here because readPos always increases
        while (readPos < arr.size) {
            if (arr[readPos] != previous) {
                arr[writePos] = arr[readPos]
                writePos++
                previous = arr[readPos]
            }
            readPos++
        }
        return writePos
    }
}


private class Task27_14Test {
    private val task = Task27_14()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.removeDuplicatesInPlace(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 2, 2, 3, 3, 3, 5), 4),
                Arguments.of(intArrayOf(), 0),
                Arguments.of(intArrayOf(1), 1),
                Arguments.of(intArrayOf(1, 1), 1),
                Arguments.of(intArrayOf(1, 2), 2),
                Arguments.of(intArrayOf(1, 1, 1), 1),
                Arguments.of(intArrayOf(1, 2, 2, 2, 3), 3)
            )
        }
    }
}