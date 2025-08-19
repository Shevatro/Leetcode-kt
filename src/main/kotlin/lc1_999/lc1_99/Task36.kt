package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/valid-sudoku
class Task36() {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val columns = Array(9) { BooleanArray(10) }
        val rows = Array(9) { BooleanArray(10) }
        val boxes = Array(9) { BooleanArray(10) }

        for (i in board.indices) {
            for (j in board[0].indices) {
                val item = board[i][j]
                if (item == '.') continue
                val itemInt = item.digitToInt()
                //column
                if (columns[i][itemInt]) return false
                columns[i][itemInt] = true

                //row
                if (rows[j][itemInt]) return false
                rows[j][itemInt] = true

                //box
                val boxI = i / 3
                val boxJ = j / 3
                val boxInd = (boxI * 3) + boxJ
                if (boxes[boxInd][itemInt]) return false
                boxes[boxInd][itemInt] = true
            }
        }
        return true
    }
}

private class Task36Test {
    private val task = Task36()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<CharArray>, expected: Boolean) {
        Assertions.assertEquals(expected, task.isValidSudoku(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(
                        charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
                        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
                    ), true
                ),
                Arguments.of(
                    arrayOf(
                        charArrayOf('8', '3', '.', '.', '7', '.', '.', '.', '.'),
                        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
                    ), false
                )
            )
        }
    }
}