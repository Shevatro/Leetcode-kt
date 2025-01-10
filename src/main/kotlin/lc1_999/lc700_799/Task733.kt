package lc1_999.lc700_799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/flood-fill/
class Task733 {
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        return Solution2(image, Point(sr, sc), color).floodFill()
    }

    private class Solution2(
        private val image: Array<IntArray>,
        startPoint: Point,
        private val color: Int
    ) {
        private var point = startPoint
        private val originalColor = image[point.i][point.j]
        private val directions = listOf(
            Direction(iDiff = -1, iLimit = -1, desc = "up"),
            Direction(iDiff = 1, iLimit = image.size, desc = "down"),
            Direction(jDiff = -1, jLimit = -1, desc = "left"),
            Direction(jDiff = 1, jLimit = image[0].size, desc = "right"),
        )

        fun floodFill(): Array<IntArray> {
            applyChanges()
            return image
        }

        private fun applyChanges() {
            val pixel = image[point.i][point.j]
            if (pixel != originalColor || pixel == color) return
            image[point.i][point.j] = color
            for (direction in directions) {
                val oldPoint = point
                val nextPoint = Point(point.i + direction.iDiff, point.j + direction.jDiff)
                if (!nextPoint.isValid(direction)) continue
                val nextPixel = image[nextPoint.i][nextPoint.j]
                if (nextPixel != originalColor) continue
                println(oldPoint.toString() + " ->" + direction.desc)
                point = nextPoint
                applyChanges()
                point = oldPoint
            }
        }
    }

    private data class Direction(
        val iDiff: Int = 0,
        val jDiff: Int = 0,
        val iLimit: Int? = null,
        val jLimit: Int? = null,
        val desc: String
    )

    private data class Point(
        val i: Int,
        var j: Int
    ) {
        fun isValid(direction: Direction): Boolean {
            return i != direction.iLimit && j != direction.jLimit
        }
    }
}

private class Task733Test {
    private val task = Task733()

    @Test
    fun test1() {
        val inputMatrix = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1))
        val actualResult = task.floodFill(inputMatrix, 1, 1, 2)
        val expectedResult = arrayOf(intArrayOf(2, 2, 2), intArrayOf(2, 2, 0), intArrayOf(2, 0, 1))
        Assertions.assertArrayEquals(expectedResult, actualResult)
    }

    @Test
    fun test2() {
        val inputMatrix = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))
        val actualResult = task.floodFill(inputMatrix, 0, 0, 0)
        val expectedResult = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))
        Assertions.assertArrayEquals(expectedResult, actualResult)
    }


    @Test
    fun test3() {
        val inputMatrix =
            arrayOf(intArrayOf(1, 0, 1, 1), intArrayOf(1, 0, 1, 1), intArrayOf(1, 0, 1, 1), intArrayOf(1, 0, 1, 1))
        val actualResult = task.floodFill(inputMatrix, 0, 0, 2)
        val expectedResult =
            arrayOf(intArrayOf(2, 0, 1, 1), intArrayOf(2, 0, 1, 1), intArrayOf(2, 0, 1, 1), intArrayOf(2, 0, 1, 1))
        Assertions.assertArrayEquals(expectedResult, actualResult)
    }

    @Test
    fun test4() {
        val inputMatrix = arrayOf(intArrayOf(1))
        val actualResult = task.floodFill(inputMatrix, 0, 0, 2)
        val expectedResult = arrayOf(intArrayOf(2))
        Assertions.assertArrayEquals(expectedResult, actualResult)
    }

    @Test
    fun test5() {
        val inputMatrix = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 2, 0), intArrayOf(1, 0, 1))
        val actualResult = task.floodFill(inputMatrix, 1, 1, 2)
        val expectedResult = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 2, 0), intArrayOf(1, 0, 1))
        Assertions.assertArrayEquals(expectedResult, actualResult)
    }
}