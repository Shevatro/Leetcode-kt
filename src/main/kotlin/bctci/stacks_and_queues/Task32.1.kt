package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task32_1() {

    fun compressArray(arr: IntArray): IntArray {
        val stack = ArrayDeque<Int>()
        for (item in arr) {
            var sum = item
            while (stack.isNotEmpty() && sum == stack.first()) {
                sum *= 2
                stack.removeFirst()
            }
            stack.addFirst(sum)
        }
        return convertStackToResultArr(stack)
    }

    private fun convertStackToResultArr(stack: ArrayDeque<Int>): IntArray {
        val resultArr = IntArray(stack.size)
        for (i in resultArr.indices) {
            resultArr[i] = stack.removeLast()
        }
        return resultArr
    }
}

private class Task32_1Test {
    private val task = Task32_1()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.compressArray(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(8, 4, 2, 2, 2, 4), intArrayOf(16, 2, 4)),
                Arguments.of(intArrayOf(4, 4, 4, 4), intArrayOf(16)),
                Arguments.of(intArrayOf(1, 2, 3, 4), intArrayOf(1, 2, 3, 4))
            )
        }
    }
}