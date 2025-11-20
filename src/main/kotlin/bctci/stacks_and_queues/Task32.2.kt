package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task32_2 {
    fun compressArray(arr: IntArray, k: Int): IntArray {
        return Solution(arr, k).compressArray()
    }

    class Solution(private val arr: IntArray, private val k: Int) {
        private val stack = ArrayDeque<Item>()
        fun compressArray(): IntArray {
            for (item in arr) {
                processItem(item)
            }
            return convertStackToResultArr(stack)
        }

        private fun processItem(sum: Int) {
            if (stack.isEmpty() || stack.first().item != sum) {
                stack.addFirst(Item(sum))
            } else if (stack.first().count < k - 1) {
                stack.addFirst(Item(sum, stack.removeFirst().count + 1))
            } else {
                stack.removeFirst()
                processItem(sum * k)
            }
        }

        private fun convertStackToResultArr(stack: ArrayDeque<Item>): IntArray {
            val resultArr = ArrayList<Int>()
            while (stack.isNotEmpty()) {
                val item = stack.removeLast()
                repeat(item.count) {
                    resultArr.add(item.item)
                }
            }
            return resultArr.toIntArray()
        }
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
                Arguments.of(intArrayOf(2, 2, 2, 2), 3, intArrayOf(6, 2)),
                Arguments.of(intArrayOf(1, 1, 2, 2, 2, 3, 3, 3, 3), 3, intArrayOf(1, 1, 6, 9, 3)),
                Arguments.of(intArrayOf(1, 1, 1, 1, 1, 1), 3, intArrayOf(3, 3))
            )
        }
    }
}