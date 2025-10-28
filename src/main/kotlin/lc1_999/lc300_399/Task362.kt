package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/design-hit-counter/

class Task362 {
    class HitCounter() {
        private val deque = ArrayDeque<Pair<Int, Int>>()
        private var count = 0
        fun hit(timestamp: Int) {
            val newTimeStamp = timestamp + 300
            trimDeque(timestamp)
            if (deque.isEmpty() || deque.last().first != newTimeStamp) {
                deque.addLast(newTimeStamp to 1)
            } else {
                val previousCount = deque.removeLast().second
                deque.addLast(newTimeStamp to previousCount + 1)
            }
            count++
        }

        fun getHits(timestamp: Int): Int {
            trimDeque(timestamp)
            return count
        }

        private fun trimDeque(timestamp: Int) {
            while (deque.isNotEmpty() && timestamp >= deque.first().first) {
                count -= deque.removeFirst().second
            }
        }
    }
}

private class Task362Test {

    @Test
    fun test1() {
        val counter = Task362.HitCounter().apply {
            hit(1)
            hit(2)
            hit(3)
        }
        Assertions.assertEquals(3, counter.getHits(4))
        counter.hit(300)
        Assertions.assertEquals(4, counter.getHits(300))
        Assertions.assertEquals(3, counter.getHits(301))
    }

    @Test
    fun test2() {
        val counter = Task362.HitCounter().apply {
            hit(1)
            hit(1)
            hit(1)
            hit(300)
        }
        Assertions.assertEquals(4, counter.getHits(300))
    }
}