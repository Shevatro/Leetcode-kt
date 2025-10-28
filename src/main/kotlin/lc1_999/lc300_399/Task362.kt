package lc1_999.lc300_399

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/design-hit-counter/

class Task362 {
    class HitCounter() {
        private val deque = ArrayDeque<Int>()
        fun hit(timestamp: Int) {
            trimDeque(timestamp)
            deque.addLast(timestamp + 300)
        }

        fun getHits(timestamp: Int): Int {
            trimDeque(timestamp)
            return deque.size
        }

        private fun trimDeque(timestamp: Int) {
            while (deque.isNotEmpty() && timestamp >= deque.first()) {
                deque.removeFirst()
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