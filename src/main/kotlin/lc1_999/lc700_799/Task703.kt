package lc1_999.lc700_799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/kth-largest-element-in-a-stream/description/
class Task703 {

    class KthLargest(k: Int, nums: IntArray) {
        private val solution = Solution(k, nums).apply { init() }

        fun add(`val`: Int): Int {
            return solution.add(`val`)
        }

        private class Solution(
            private val k: Int,
            private val nums: IntArray
        ) {
            private val topItemsMinHeap = PriorityQueue<Int>()

            fun init() {
                nums.forEach { add(it) }
            }

            fun add(`val`: Int): Int {
                val kItem = topItemsMinHeap.peek()
                if (topItemsMinHeap.size < k) {
                    topItemsMinHeap.add(`val`)
                } else if (`val` > kItem) {
                    topItemsMinHeap.remove()
                    topItemsMinHeap.add(`val`)
                }
                return topItemsMinHeap.peek()
            }
        }
    }
}

private class Task703Test {

    @Test
    fun test1() {
        val input = Task703.KthLargest(3, intArrayOf(4, 5, 8, 2))
        Assertions.assertEquals(4, input.add(3))
        Assertions.assertEquals(5, input.add(5))
        Assertions.assertEquals(5, input.add(10))
        Assertions.assertEquals(8, input.add(9))
        Assertions.assertEquals(8, input.add(4))
    }

    @Test
    fun test2() {
        val input = Task703.KthLargest(4, intArrayOf(7, 7, 7, 7, 8, 3))
        Assertions.assertEquals(7, input.add(2))
        Assertions.assertEquals(7, input.add(10))
        Assertions.assertEquals(7, input.add(9))
        Assertions.assertEquals(8, input.add(9))
    }

    @Test
    fun test3() {
        val input = Task703.KthLargest(3, intArrayOf(4, 1, 5, 8, 2))
        Assertions.assertEquals(4, input.add(3))
        Assertions.assertEquals(5, input.add(5))
        Assertions.assertEquals(5, input.add(10))
        Assertions.assertEquals(8, input.add(9))
        Assertions.assertEquals(8, input.add(4))
    }

    @Test
    fun test4() {
        val input = Task703.KthLargest(1, intArrayOf())
        Assertions.assertEquals(-3, input.add(-3))
        Assertions.assertEquals(-2, input.add(-2))
        Assertions.assertEquals(-2, input.add(-4))
        Assertions.assertEquals(0, input.add(0))
        Assertions.assertEquals(4, input.add(4))
    }

    @Test
    fun test5() {
        val input = Task703.KthLargest(2, intArrayOf(0))
        Assertions.assertEquals(-1, input.add(-1))
        Assertions.assertEquals(0, input.add(1))
        Assertions.assertEquals(0, input.add(-2))
        Assertions.assertEquals(0, input.add(-4))
        Assertions.assertEquals(1, input.add(3))
    }
}