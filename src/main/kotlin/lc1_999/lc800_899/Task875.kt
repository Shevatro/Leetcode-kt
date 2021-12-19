package lc1_999.lc800_899
//Not solved, repeat
//https://leetcode.com/problems/koko-eating-bananas/
import kotlin.math.ceil

class Task875 {
    fun minEatingSpeedRecursion(piles: IntArray, h: Int): Int {
        val start = 1
        val end = piles.maxOrNull() ?: -1
        return minEatingSpeedRecursion(piles, h, start, end)
    }

    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var start = 1
        var end = piles.maxOrNull() ?: -1
        while (end >= start) {
            if (start == end) return start
            val middle = (start + end) / 2
            if (canBeEatenInTime(piles, h, middle)) {
                end = middle
            } else {
                start = middle + 1
            }
        }
        return -1
    }

    private fun minEatingSpeedRecursion(piles: IntArray, h: Int, start: Int, end: Int): Int {
        val middle = (start + end) / 2
        println("start:" + start + " end:" + end + " middle:" + middle)
        if (end == start) return start
        if (start > end) return -1
        return if (canBeEatenInTime(piles, h, middle)) {
            minEatingSpeedRecursion(piles, h, start, middle)
        } else {
            minEatingSpeedRecursion(piles, h, middle + 1, end)
        }
    }

    private fun canBeEatenInTime(piles: IntArray, h: Int, k: Int): Boolean {
        var sum = 0
        for (pile in piles) {
            sum += ceil(pile.toDouble() / k).toInt()
        }
        return sum <= h
    }
}

fun main() {
    val obj = Task875()
    println(obj.minEatingSpeed(intArrayOf(3, 6, 7, 11), 8))
    println(obj.minEatingSpeed(intArrayOf(30, 11, 23, 4, 20), 5))
    println(obj.minEatingSpeed(intArrayOf(30, 11, 23, 4, 20), 6))
    println(obj.minEatingSpeed(intArrayOf(3, 6, 7, 10), 8))
}