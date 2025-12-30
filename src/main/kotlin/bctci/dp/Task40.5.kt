package bctci.dp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Task40_5 {
    fun getDealerBusts(): Int {
        return Solution().getDealerBusts()
    }

    private class Solution {
        private val memo = IntArray(16)

        fun getDealerBusts(): Int {
            return dp(0)
        }

        private fun dp(sum: Int): Int {
            if (sum > 21) return 1
            if (sum in 16..21) return 0
            if (memo[sum] != 0) return memo[sum]
            var curSum = 0
            for (i in 1..10) {
                curSum += dp(sum + i)
            }
            memo[sum] = curSum
            return curSum
        }
    }
}


private class Task40_5Test {
    private val task = Task40_5()

    @Test
    fun test() {
        Assertions.assertEquals(100081, task.getDealerBusts())
    }
}