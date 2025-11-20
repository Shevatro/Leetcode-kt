package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task32_3 {
    class ViewerCounter(private val window: Int) {
        private val viewers = mutableMapOf(
            "subscriber" to Data(),
            "guest" to Data(),
            "follower" to Data()
        )

        fun join(t: Int, vt: String) {
            trimDeque(t, vt)
            val newWindowSize = t + window
            val item = requireNotNull(viewers[vt])
            val deque = item.deque
            if (deque.isEmpty() || deque.last().first != newWindowSize) {
                deque.addLast(newWindowSize to 1)
            } else {
                val previousCount = deque.removeLast().second
                deque.addLast(newWindowSize to previousCount + 1)
            }
            item.count++
        }

        fun getViewers(t: Int, vt: String): Int {
            trimDeque(t, vt)
            return viewers[vt]!!.count
        }

        private fun trimDeque(t: Int, vt: String) {
            val item = viewers[vt] ?: return
            val deque = item.deque
            while (deque.isNotEmpty() && t > deque.first().first) {
                item.count -= deque.removeFirst().second
            }
        }
    }

    private data class Data(
        val deque: ArrayDeque<Pair<Int, Int>> = ArrayDeque(),
        var count: Int = 0
    )
}

private class Task32_3Test {

    @Test
    fun test() {
        val counter = Task32_3.ViewerCounter(10).apply {
            join(1, "subscriber")
            join(1, "guest")
            join(2, "follower")
            join(2, "follower")
            join(2, "follower")
            join(3, "follower")
        }
        Assertions.assertEquals(1, counter.getViewers(10, "subscriber"))
        Assertions.assertEquals(1, counter.getViewers(10, "guest"))
        Assertions.assertEquals(4, counter.getViewers(10, "follower"))
        Assertions.assertEquals(1, counter.getViewers(13, "follower"))
    }
}