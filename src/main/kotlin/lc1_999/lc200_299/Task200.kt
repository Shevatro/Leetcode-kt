package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/number-of-islands/
class Task200 {
    fun numIslands(grid: Array<CharArray>): Int {
        return Solution2(grid).numIslands()
    }

    private class Solution2(private val grid: Array<CharArray>) {
        private var amount = 0
        private val visited = mutableSetOf<IJ>()
        fun numIslands(): Int {
            for (i in grid.indices) {
                for (j in grid[0].indices) {
//                    println("$i $j")
                    if (grid[i][j] == '1') {
                        visitNeighboards(IJ(i, j))
                        amount++
                    }
                }
            }
            return amount
        }


        private fun visitNeighboards(ij: IJ) {
            val queue = ArrayDeque<IJ>()
            queue.addLast(ij)
            visited.add(ij)
            while (queue.isNotEmpty()) {
                val (i,j) = queue.removeFirst()
//                println("investigate:($i, $j)")
                grid[i][j] = '0'
                moveRight(i, j)?.let { queue.addLast(it) }
                moveUp(i, j)?.let { queue.addLast(it) }
                moveLeft(i, j)?.let { queue.addLast(it) }
                moveDown(i, j)?.let { queue.addLast(it) }

            }
        }

        private fun moveRight(i: Int, j: Int): IJ? {
            if (i != grid.size - 1) {
                return create2DifValid(i + 1, j)
            }
            return null
        }

        private fun moveLeft(i: Int, j: Int): IJ? {
            if (i != 0) {
                return create2DifValid(i - 1, j)
            }
            return null
        }

        private fun moveUp(i: Int, j: Int): IJ? {
            if (j != 0) {
                return create2DifValid(i, j - 1)
            }
            return null
        }

        private fun moveDown(i: Int, j: Int): IJ? {
            if (j != grid[0].size - 1) {
                return create2DifValid(i, j + 1)
            }
            return null
        }

        private fun create2DifValid(i: Int, j: Int): IJ? {
            val ij = IJ(i, j)
            if (grid[i][j] == '1' && !visited.contains(ij)) {
                visited.add(ij)
                return ij
            }
            return null
        }

        private data class IJ(val i: Int, val j: Int)
    }
}

private class Task200Test {
    private val task = Task200()

    @Test
    fun numIslandsTest1() {
        val grid = arrayOf(
            charArrayOf('1', '1', '1', '1', '0'), charArrayOf('1', '1', '0', '1', '0'),
            charArrayOf('1', '1', '0', '0', '0'), charArrayOf('0', '0', '0', '0', '0')
        )
        val result = task.numIslands(grid)
        Assertions.assertEquals(1, result)
    }

    @Test
    fun numIslandsTest2() {
        val grid = arrayOf(
            charArrayOf('1', '1', '0', '0', '0'), charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '1', '0', '0'), charArrayOf('0', '0', '0', '1', '1')
        )
        val result = task.numIslands(grid)
        Assertions.assertEquals(3, result)
    }

    @Test()
    fun numIslandsTest3() {
        val grid = arrayOf(
            charArrayOf('1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','0','1','0','1','1'),
            charArrayOf('0','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','0'),
            charArrayOf('1','0','1','1','1','0','0','1','1','0','1','1','1','1','1','1','1','1','1','1'),
            charArrayOf('1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),
            charArrayOf('1','0','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),
            charArrayOf('1','0','1','1','1','1','1','1','0','1','1','1','0','1','1','1','0','1','1','1'),
            charArrayOf('0','1','1','1','1','1','1','1','1','1','1','1','0','1','1','0','1','1','1','1'),
            charArrayOf('1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','1','1'),
            charArrayOf('1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1'),
            charArrayOf('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),
            charArrayOf('0','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1'),
            charArrayOf('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),
            charArrayOf('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),
            charArrayOf('1','1','1','1','1','0','1','1','1','1','1','1','1','0','1','1','1','1','1','1'),
            charArrayOf('1','0','1','1','1','1','1','0','1','1','1','0','1','1','1','1','0','1','1','1'),
            charArrayOf('1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','0'),
            charArrayOf('1','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','0'),
            charArrayOf('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),
            charArrayOf('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'),
            charArrayOf('1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1')
        )
        val result = task.numIslands(grid)
        Assertions.assertEquals(1, result)
    }

}