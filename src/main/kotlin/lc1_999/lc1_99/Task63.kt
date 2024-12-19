package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/unique-paths-ii/
class Task63 {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
//        return TopDownSolution(obstacleGrid).uniquePathsWithObstacles(obstacleGrid.size - 1, obstacleGrid[0].size - 1)
        return BottomUpSolution(obstacleGrid).uniquePathsWithObstacles()
    }

    private class TopDownSolution(private val obstacleGrid: Array<IntArray>) {
        private val cache = Array(obstacleGrid.size) { IntArray(obstacleGrid[0].size) }
        fun uniquePathsWithObstacles(i: Int, j: Int): Int {
            if (i == 0 && j == 0) {
                return if (obstacleGrid[0][0] == 1) 0 else 1
            }
            if (i < 0 || j < 0) return 0
            if (obstacleGrid[i][j] == 1) return 0
            if (cache[i][j] == 0) {
                cache[i][j] = uniquePathsWithObstacles(i - 1, j) + uniquePathsWithObstacles(i, j - 1)
            }
            return cache[i][j]
        }
    }

    private class BottomUpSolution(private val obstacleGrid: Array<IntArray>) {
        fun uniquePathsWithObstacles(): Int {
            if (obstacleGrid[0][0] == 1) return 0
            obstacleGrid[0][0] = 1
            fillFirstRow()
            fillFirstColumn()
            fillEverythingElse()
            return obstacleGrid[obstacleGrid.size - 1][obstacleGrid[0].size - 1]
        }

        private fun fillFirstRow() {
            for (c in 1 until obstacleGrid[0].size) {
                if (obstacleGrid[0][c] == 1) {
                    obstacleGrid[0][c] = 0
                } else {
                    obstacleGrid[0][c] = obstacleGrid[0][c - 1]
                }
            }
        }

        private fun fillFirstColumn() {
            for (r in 1 until obstacleGrid.size) {
                if (obstacleGrid[r][0] == 1) {
                    obstacleGrid[r][0] = 0
                } else {
                    obstacleGrid[r][0] = obstacleGrid[r - 1][0]
                }
            }
        }

        private fun fillEverythingElse() {
            for (c in 1 until obstacleGrid.size) {
                for (r in 1 until obstacleGrid[0].size) {
                    if (obstacleGrid[c][r] == 1) {
                        obstacleGrid[c][r] = 0
                    } else {
                        obstacleGrid[c][r] = obstacleGrid[c][r - 1] + obstacleGrid[c - 1][r]
                    }
                }
            }
        }
    }
}

private class Task63Test {
    private val task = Task63()

    @Test
    fun uniquePathsWithObstaclesTest1() {
        val input = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0))
        val actual = task.uniquePathsWithObstacles(input)
        Assertions.assertEquals(2, actual)
    }

    @Test
    fun uniquePathsWithObstaclesTest2() {
        val input = arrayOf(intArrayOf(0, 1), intArrayOf(0, 0))
        val actual = task.uniquePathsWithObstacles(input)
        Assertions.assertEquals(1, actual)
    }

    @Test
    fun uniquePathsWithObstaclesTest3() {
        val input = arrayOf(intArrayOf(1))
        val actual = task.uniquePathsWithObstacles(input)
        Assertions.assertEquals(0, actual)
    }

    @Test
    fun uniquePathsWithObstaclesTest4() {
        val input = arrayOf(intArrayOf(0))
        val actual = task.uniquePathsWithObstacles(input)
        Assertions.assertEquals(1, actual)
    }

    @Test
    fun uniquePathsWithObstaclesTest5() {
        val input = arrayOf(intArrayOf(1, 0))
        val actual = task.uniquePathsWithObstacles(input)
        Assertions.assertEquals(0, actual)
    }

    @Test
    fun uniquePathsWithObstaclesTest6() {
        val input = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0),
            intArrayOf(1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1),
            intArrayOf(0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0),
            intArrayOf(1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
            intArrayOf(0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1),
            intArrayOf(0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0),
            intArrayOf(0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0),
            intArrayOf(1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0),
            intArrayOf(1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0)
        )
        val actual = task.uniquePathsWithObstacles(input)
        Assertions.assertEquals(13594824, actual)
    }
}