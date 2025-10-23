package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task32_2() {

    fun compressArray(arr: IntArray, k: Int): IntArray {
        val stack = ArrayDeque<Item>()
        stack.addFirst(Item(arr[0]))
        var i = 1
        while (i != arr.size) {
            val previousItem = stack.first()
            if (previousItem.count == k) {
                repeat(k) { stack.removeFirst() }
                val item = previousItem.item * k
                val count = if (stack.isNotEmpty() && stack.first().item == item) stack.first().count + 1 else 1

                stack.addFirst(Item(item, count))
                //found match and we need to revaluate
                if (count != k) i++
            } else if (previousItem.item == arr[i]) {
                val newCount = previousItem.count + 1
                stack.addFirst(Item(arr[i], newCount))
                //found match and we need to revaluate
                if (newCount != k) i++
            } else {
                stack.addFirst(Item(arr[i]))
                i++
            }
        }
        return convertStackToResultArr(stack)
    }

    private fun convertStackToResultArr(stack: ArrayDeque<Item>): IntArray {
        val resultArr = IntArray(stack.size)
        for (i in resultArr.indices) {
            resultArr[i] = stack.removeLast().item
        }
        return resultArr
    }

    private data class Item(
        val item: Int,
        val count: Int = 1
    )
}

private class Task32_2Test {
    private val task = Task32_2()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: IntArray, k: Int, expected: IntArray) {
        Assertions.assertArrayEquals(expected, task.compressArray(input, k))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(intArrayOf(1, 9, 9, 3, 3, 3, 4), 3, intArrayOf(1, 27, 4)),
                Arguments.of(intArrayOf(8, 4, 2, 2), 2, intArrayOf(16)),
                Arguments.of(intArrayOf(4, 4, 4, 4), 5, intArrayOf(4, 4, 4, 4)),
                Arguments.of(intArrayOf(2, 2, 2), 2, intArrayOf(4, 2)),
                Arguments.of(intArrayOf(2, 2, 2, 2), 3, intArrayOf(6,2)),
                Arguments.of(intArrayOf(1,1,2,2,2,3,3,3,3), 3, intArrayOf(1,1,6,9,3)),
                Arguments.of(intArrayOf(1,1,1,1,1,1), 3, intArrayOf(3,3))
            )
        }
    }
}