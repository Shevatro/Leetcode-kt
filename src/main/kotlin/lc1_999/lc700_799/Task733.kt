package lc1_999.lc700_799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/flood-fill/
class Task733 {
    fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
        return Solution2(image, sr, sc, color).floodFill()
    }

    private class Solution2(
        private val image: Array<IntArray>,
        private val iStart: Int,
        private val jStart: Int,
        private val color: Int
    ) {
        private var iCurrent = iStart
        private var jCurrent = jStart
        private val originalColor = image[iCurrent][jCurrent]
        private val directions = listOf(
            Direction(iDiff = -1, iLimit = -1, desc = "up"),
            Direction(iDiff = 1, iLimit = image.size, desc = "down"),
            Direction(jDiff = -1, jLimit = -1, desc = "left"),
            Direction(jDiff = 1, jLimit = image[0].size, desc = "right"),
        )
        private var k = 0
        private val size = image.size * image[0].size
        fun floodFill(): Array<IntArray> {
            applyChanges()
            return image
        }

        private fun applyChanges() {
            println("k:" + k + " i:" + iCurrent + " j:" + jCurrent)
            if (iCurrent < 0 || jCurrent < 0 || iCurrent >= image.size || jCurrent >= image[0].size) return
            if (image[iCurrent][jCurrent] != originalColor || image[iCurrent][jCurrent] == color) return
            if (k > size) return
            k++
            image[iCurrent][jCurrent] = color
            println("i:" + iCurrent + " j:" + jCurrent)
            println(image.map { it.toList() + "\n" }.toList())
            for (direction in directions) {
                val iNew = iCurrent + direction.iDiff
                val jNew = jCurrent + direction.jDiff
                if (iNew == direction.iLimit || jNew == direction.jLimit || image[iNew][jNew] != originalColor) continue
                iCurrent = iNew
                jCurrent = jNew
                println("go:" + direction.desc)
                applyChanges()
                iCurrent = iNew - direction.iDiff
                jCurrent = jNew - direction.jDiff
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