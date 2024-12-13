package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

//From Cracking The Coding Interview, Solved
//https://leetcode.com/problems/number-of-islands/
class Task200 {
    fun numIslands(grid: Array<CharArray>): Int {
        return Solution(grid).numIslands()
//        return Solution2(grid).numIslands()
    }

    private class Solution2(private val grid: Array<CharArray>) {
        private val visited = mutableSetOf<IJ>()
        private val directions = listOf(
            Direction(iDiff = 1, iLimit = grid.lastIndex), //right
            Direction(jDiff = -1, jLimit = 0), //up
            Direction(iDiff = -1, iLimit = 0), //left
            Direction(jDiff = 1, jLimit = grid[0].lastIndex), //down
        )
        private val queue = ArrayDeque<IJ>()
        fun numIslands(): Int {
            var amount = 0
            for (i in grid.indices) {
                for (j in grid[0].indices) {
//                    println("$i $j")
                    if (grid[i][j] == '1') {
                        bfs(IJ(i, j))
                        amount++
                    }
                }
            }
            return amount
        }


        private fun bfs(ij: IJ) {
            queue.addLast(ij)
            visited.add(ij)
            while (queue.isNotEmpty()) {
                val (i, j) = queue.removeFirst()
//                println("investigate:($i, $j)")
                grid[i][j] = '0'
                visitNeighbours(i, j)

            }
        }

        private fun visitNeighbours(i: Int, j: Int) {
            for (direction in directions) {
                if (i != direction.iLimit && j != direction.jLimit) {
                    val ij = IJ(i + direction.iDiff, j + direction.jDiff)
                    if (grid[ij.i][ij.j] == '1' && !visited.contains(ij)) {
                        visited.add(ij)
                        queue.addLast(ij)
                    }
                }
            }
        }
    }

    private class Solution(private val grid: Array<CharArray>) {
        private val directions = listOf(
            Direction(iDiff = 1, iLimit = grid.lastIndex), //right
            Direction(jDiff = -1, jLimit = 0), //up
            Direction(iDiff = -1, iLimit = 0), //left
            Direction(jDiff = 1, jLimit = grid[0].lastIndex), //down
        )
        fun numIslands(): Int {
            var amount = 0
            for (i in grid.indices) {
                for (j in grid[0].indices) {
                    if (grid[i][j] == '1') {
                        dfs(IJ(i, j))
                        amount++
                    }
                }
            }
            return amount
        }

        private fun dfs(ij: IJ){
            val (i,j) = ij
            grid[i][j]='0'
            for (destination in directions){
                if (i!= destination.iLimit && j!=destination.jLimit){
                    val newItem = IJ(i+destination.iDiff, j+destination.jDiff)
                    if (grid[newItem.i][newItem.j]=='1') dfs(IJ(i+destination.iDiff, j+destination.jDiff))
                }
            }
        }
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

private data class IJ(val i: Int, val j: Int)
private data class Direction(
    val iDiff: Int = 0, val iLimit: Int? = null,
    val jDiff: Int = 0, val jLimit: Int? = null
)