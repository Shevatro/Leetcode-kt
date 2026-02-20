package bctci.heaps

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.PriorityQueue
import java.util.stream.Stream
import kotlin.math.min

class Task37_8 {

    fun calcSumOfFirstKPrimePowers(primes: IntArray, k: Int): Int {
        val primesSorted = primes.sortedArray()
        //primeNum to curSum
        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        //we don't want to add more items than k
        for (i in 0 until min(primes.size, k)) {
            minHeap.add(primesSorted[i] to primesSorted[i])
        }

        var sum = 0
        var itemsLeft = k
        while (minHeap.isNotEmpty() && itemsLeft > 0) {
            val top = minHeap.poll()
            sum = (top.second + sum) % 1_000_000_007
            minHeap.add(top.first to top.second * top.first)
            itemsLeft--
        }
        return sum
    }
}


private class Task37_8Test {
    private val task = Task37_8()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: IntArray, input2: Int, expected: Int) {
        Assertions.assertEquals(expected, task.calcSumOfFirstKPrimePowers(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(2), 1, 2),
                Arguments.of(intArrayOf(5), 3, 155),
                Arguments.of(intArrayOf(2, 3), 7, 69),
                Arguments.of(intArrayOf(2, 3), 0, 0),
                Arguments.of(intArrayOf(5, 7, 11, 13, 17, 19), 4, 36),
                Arguments.of(intArrayOf(19, 17, 13, 11, 7, 5), 4, 36)
            )
        }
    }
}