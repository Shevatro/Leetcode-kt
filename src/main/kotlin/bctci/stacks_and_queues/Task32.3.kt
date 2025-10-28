package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
class Task32_3() {
    class ViewerCounter(private val window: Int) {
        private val viewers = mutableMapOf(
            "subscriber" to ArrayDeque<Int>(),
            "guest" to ArrayDeque(),
            "follower" to ArrayDeque()
        )

        fun join(t: Int, vt: String) {
            trimDeque(t, vt)
            viewers[vt]?.addLast(t + window)
        }

        fun getViewers(t: Int, vt: String): Int {
            trimDeque(t, vt)
            return viewers[vt]?.size ?: 0
        }

        private fun trimDeque(t: Int, vt: String) {
            val deque = viewers[vt] ?: return
            while (deque.isNotEmpty() && t > deque.first()) {
                deque.removeFirst()
            }
        }
    }
}

private class Task32_3Test {

    @Test
    fun test() {
        val counter = Task32_3.ViewerCounter(10)
        counter.join(1, "subscriber")
        counter.join(1, "guest")
        counter.join(2, "follower")
        counter.join(2, "follower")
        counter.join(2, "follower")
        counter.join(3, "follower")
        Assertions.assertEquals(1,counter.getViewers(10, "subscriber"))
        Assertions.assertEquals(1,counter.getViewers(10, "guest"))
        Assertions.assertEquals(4, counter.getViewers(10, "follower"))
        Assertions.assertEquals(1, counter.getViewers(13, "follower"))
    }
}