package bctci.greedy

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task41_4 {

    fun findMinimumTripletMedians(arr: IntArray): Int {
        arr.sort()
        var sum = 0
        val stopInd = arr.size - arr.size / 3
        //every other item, pattern: [startPos, starPos+1, endPos]
        for (i in 1 until stopInd step 2) {
            sum += arr[i]
        }
        return sum
    }
}


private class Task41_4Test {
    private val task = Task41_4()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: Int) {
        Assertions.assertEquals(expected, task.findMinimumTripletMedians(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {

            return Stream.of(
                Arguments.of(intArrayOf(6, 5, 8, 2, 1, 9), 8),
                Arguments.of(intArrayOf(6, 5, 8, 2, 1, 9, 12, 15, 14), 17),
                Arguments.of(intArrayOf(1, 2, 3), 2),
                Arguments.of(intArrayOf(10, 20, 60, 30, 40, 50), 60),
                Arguments.of(intArrayOf(1, 3, 5, 7, 9, 11, 13, 15, 17), 21),
                Arguments.of(intArrayOf(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24), 40),
                Arguments.of(intArrayOf(10, 11, 12, 13, 14, 15, 1, 2, 3, 4, 5, 6, 7, 8, 9), 30),
                Arguments.of(intArrayOf(5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90), 210)
            )
        }
    }
}