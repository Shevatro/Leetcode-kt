package lc1000_1999.lc1600_1699

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.math.max

//From Cracking The Coding Interview, Solved but optimal
//https://leetcode.com/problems/maximum-height-by-stacking-cuboids/description/
class Task1691 {
    fun maxHeight(cuboids: Array<IntArray>): Int {
        return Solution2(cuboids).maxHeight()
    }

    private class Solution2(private val cuboids: Array<IntArray>) {
        private val heights = IntArray(cuboids.size)
        private var maxHeight = 0
        fun maxHeight(): Int {
            sort()
            for (i in cuboids.indices) {
                maxHeight = max(maxHeight, backtrack(i))
            }
            return maxHeight
        }

        private fun sort() {
            for (cuboid in cuboids) {
                cuboid.sort()
            }
            cuboids.sortByDescending { it[0] + it[1] + it[2] }
        }

        private fun backtrack(index: Int): Int {
            if (index < cuboids.size && heights[index] > 0) {
                return heights[index]
            }
            var height = 0
            for (i in index + 1 until cuboids.size) {
                if (!isValid(index, i)) continue
                height = max(backtrack(i), height)
            }
            height += cuboids[index][2]
            heights[index] = height
            return height
        }

        private fun isValid(prevInd: Int, currInd: Int): Boolean {
            val prev = cuboids[prevInd]
            val curr = cuboids[currInd]
            return prev[0] >= curr[0] && prev[1] >= curr[1] && prev[2] >= curr[2]
        }
    }
}

private class Task1691Test {
    private val task = Task1691()

    @Test
    fun test1() {
        val input = arrayOf(intArrayOf(50, 45, 20), intArrayOf(95, 37, 53), intArrayOf(45, 23, 12))
        val actualResult = task.maxHeight(input)
        Assertions.assertEquals(190, actualResult)
    }

    @Test
    fun test2() {
        val input = arrayOf(intArrayOf(38, 25, 45), intArrayOf(76, 35, 3))
        val actualResult = task.maxHeight(input)
        Assertions.assertEquals(76, actualResult)
    }

    @Test
    fun test3() {
        val input = arrayOf(
            intArrayOf(7, 11, 17), intArrayOf(7, 17, 11), intArrayOf(7, 17, 11), intArrayOf(7, 17, 11),
            intArrayOf(7, 17, 11), intArrayOf(7, 17, 11)
        )
        val actualResult = task.maxHeight(input)
        Assertions.assertEquals(102, actualResult)
    }

    @Test
    fun test4() {
        val input = arrayOf(intArrayOf(34, 24, 45), intArrayOf(76, 35, 25))
        val actualResult = task.maxHeight(input)
        Assertions.assertEquals(121, actualResult)
    }

    @Test
    fun test5() {
        val input = arrayOf(
            intArrayOf(44, 64, 42), intArrayOf(12, 1, 69), intArrayOf(5, 49, 80), intArrayOf(89, 56, 59),
            intArrayOf(6, 8, 64), intArrayOf(93, 26, 27), intArrayOf(61, 12, 59), intArrayOf(43, 9, 30),
            intArrayOf(12, 77, 93), intArrayOf(42, 82, 20), intArrayOf(86, 99, 33), intArrayOf(41, 26, 12),
            intArrayOf(20, 79, 68), intArrayOf(68, 73, 12), intArrayOf(82, 18, 80), intArrayOf(99, 7, 91),
            intArrayOf(31, 16, 6), intArrayOf(97, 43, 39), intArrayOf(22, 87, 49), intArrayOf(80, 70, 94),
            intArrayOf(84, 16, 82), intArrayOf(18, 71, 55), intArrayOf(15, 75, 18), intArrayOf(10, 79, 40),
            intArrayOf(99, 77, 74), intArrayOf(8, 71, 90), intArrayOf(38, 49, 57), intArrayOf(80, 43, 52),
            intArrayOf(66, 82, 20), intArrayOf(94, 66, 61), intArrayOf(34, 10, 14), intArrayOf(64, 30, 78),
            intArrayOf(30, 15, 4), intArrayOf(21, 31, 44), intArrayOf(81, 77, 20), intArrayOf(12, 96, 31),
            intArrayOf(82, 46, 80), intArrayOf(76, 45, 35), intArrayOf(11, 19, 7), intArrayOf(66, 52, 47),
            intArrayOf(41, 82, 19), intArrayOf(86, 96, 70), intArrayOf(67, 7, 76), intArrayOf(94, 85, 36),
            intArrayOf(54, 24, 23), intArrayOf(77, 80, 72), intArrayOf(13, 94, 96), intArrayOf(81, 79, 21),
            intArrayOf(21, 11, 54), intArrayOf(14, 5, 62), intArrayOf(24, 25, 90), intArrayOf(28, 36, 23),
            intArrayOf(67, 52, 14), intArrayOf(13, 99, 18), intArrayOf(50, 85, 71), intArrayOf(29, 19, 81),
            intArrayOf(17, 2, 78), intArrayOf(50, 73, 42), intArrayOf(10, 71, 90), intArrayOf(48, 90, 68),
            intArrayOf(63, 35, 86), intArrayOf(79, 66, 93), intArrayOf(21, 88, 26), intArrayOf(73, 94, 65),
            intArrayOf(53, 21, 41), intArrayOf(63, 28, 87), intArrayOf(11, 44, 44)
        )
        val actualResult = task.maxHeight(input)
        Assertions.assertEquals(946, actualResult)
    }
}