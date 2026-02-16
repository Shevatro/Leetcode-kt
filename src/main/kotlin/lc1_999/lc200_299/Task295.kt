package lc1_999.lc200_299

import lc1_999.lc200_299.Task295.MedianFinder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*


//Similar to Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/find-median-from-data-stream/description/
class Task295 {
    class MedianFinder {
        private val minHeap = PriorityQueue<Int>()
        private val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())

        /*ex. add(193): maxHeap[193] minHeap[]
              add(140): maxHeap[140] minHeap[193]
              add(132): maxHeap[140, 132] minHeap[193] -> 140
              add(291): maxHeap[140, 132] minHeap[193, 291]
              add(274): maxHeap[193, 140, 132] minHeap[274, 291]
              add(223): maxHeap[193, 140, 132] minHeap[223, 274, 291] -> (193+223)/2 = 208
        */

        fun addNum(num: Int) {
            //add to a maxHeap by default
            maxHeap.add(num)
            //copy the biggest value to minHeap
            minHeap.add(maxHeap.poll())

            //if minHeap is overloaded, copy the smallest value to maxHeap
            if (minHeap.size > maxHeap.size) {
                maxHeap.add(minHeap.poll())
            }
        }

        fun findMedian(): Double {
            return if ((minHeap.size + maxHeap.size) % 2 == 0) {
                (minHeap.peek() + maxHeap.peek()) / 2.0
            } else if (minHeap.size > maxHeap.size) {
                minHeap.peek().toDouble()
            } else {
                maxHeap.peek().toDouble()
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