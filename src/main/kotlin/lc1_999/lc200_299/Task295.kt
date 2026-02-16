package lc1_999.lc200_299

import lc1_999.lc200_299.Task295.MedianFinder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.math.abs


//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/find-median-from-data-stream/description/
class Task295 {
    class MedianFinder() {
        private val minHeap = PriorityQueue<Int>(compareBy { it })
        private val maxHeap = PriorityQueue<Int>(compareByDescending { it })

        fun addNum(num: Int) {
            if (minHeap.isEmpty() && maxHeap.isEmpty()) {
                minHeap.add(num)
            } else if (minHeap.isNotEmpty() && num <= minHeap.first()) {
                maxHeap.add(num)
            } else if (maxHeap.isNotEmpty() && num > maxHeap.first()) {
                minHeap.add(num)
            } else {
                maxHeap.add(num)
            }

            //violates rules, swap
            if (minHeap.isNotEmpty() && maxHeap.isNotEmpty() && minHeap.first() < maxHeap.first()) {
                val maxInMinHeap = minHeap.poll()
                val minInMaxHeap = maxHeap.poll()
                maxHeap.add(maxInMinHeap)
                minHeap.add(minInMaxHeap)
            }
            //rebalance
            if (abs(minHeap.size - maxHeap.size) > 1) {
                if (minHeap.size > maxHeap.size) {
                    maxHeap.add(minHeap.poll())
                } else {
                    minHeap.add(maxHeap.poll())
                }
            }
        }

        fun findMedian(): Double {
            return if ((minHeap.size + maxHeap.size) % 2 == 0) {
                (minHeap.first() + maxHeap.first()) / 2.0
            } else if (minHeap.size > maxHeap.size) {
                minHeap.first().toDouble()
            } else {
                maxHeap.first().toDouble()
            }
        }
    }
}

private class Task295Test {

    @Test
    fun test1() {
        val medianFinder = MedianFinder().apply {
            addNum(1) // arr = [1]
            addNum(2) // arr = [1, 2]
        }
        Assertions.assertEquals(1.5, medianFinder.findMedian()) // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3) // arr[1, 2, 3]
        Assertions.assertEquals(2.0, medianFinder.findMedian())
    }

    @Test
    fun test2() {
        val medianFinder = MedianFinder().apply {
            addNum(1)
            addNum(2)
        }
        Assertions.assertEquals(1.5, medianFinder.findMedian())
        medianFinder.addNum(3)
        Assertions.assertEquals(2.0, medianFinder.findMedian())
        medianFinder.apply {
            addNum(8)
            addNum(4)
        }
        Assertions.assertEquals(3.0, medianFinder.findMedian())
    }

    @Test
    fun test3() {
        val medianFinder = MedianFinder().apply {
            addNum(193)
            addNum(140)
            addNum(132)
        }
        Assertions.assertEquals(140.0, medianFinder.findMedian())
        medianFinder.apply {
            addNum(291)
            addNum(274)
            addNum(223)
        }
        Assertions.assertEquals(208.0, medianFinder.findMedian())
    }
}