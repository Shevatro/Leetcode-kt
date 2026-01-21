package lc1_999.lc1_99

import common.IntSinglyNode
import common.equals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import toIntSinglyNode
import java.util.TreeMap
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/merge-k-sorted-lists/
class Task23 {
    fun mergeKLists(lists: Array<IntSinglyNode?>): IntSinglyNode? {
        return Solution(lists).mergeKLists()
    }

    private class Solution(
        private val lists: Array<IntSinglyNode?>
    ) {
        private val sortedMap = TreeMap<Int, Int>()
        fun mergeKLists(): IntSinglyNode? {
            fillOutSortedMap()
            return mergeMapToLinkedList()
        }

        private fun fillOutSortedMap() {
            for (head in lists) {
                var node: IntSinglyNode? = head
                while (node != null) {
                    val item = node.`val`
                    sortedMap[item] = (sortedMap[item] ?: 0) + 1
                    node = node.next
                }
            }
        }

        private fun mergeMapToLinkedList(): IntSinglyNode? {
            val head = IntSinglyNode(-1)
            var node = head
            for ((key, value) in sortedMap) {
                repeat(value) {
                    node.next = IntSinglyNode(key)
                    node = requireNotNull(node.next)
                }
            }
            return head.next
        }
    }
}

private class Task23Test {
    private val task = Task23()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray?>, expected: IntArray) {
        val inputLL = input.map { it?.toIntSinglyNode() }.toTypedArray()
        val result = task.mergeKLists(inputLL)
        Assertions.assertEquals(true, result.equals(expected.toIntSinglyNode()))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(intArrayOf(1, 4, 5), intArrayOf(1, 3, 4), intArrayOf(2, 6)),
                    intArrayOf(1, 1, 2, 3, 4, 4, 5, 6)
                ),
                Arguments.of(emptyArray<IntArray>(), intArrayOf()),
                Arguments.of(arrayOf(intArrayOf()), intArrayOf())
            )
        }
    }
}