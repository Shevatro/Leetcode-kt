package bctci.two_pointers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task27_16 {

    fun solveDutchFlagProblem(arr: CharArray) {
        return Solution(arr).solveDutchFlagProblem()
    }

    private class Solution(private val arr: CharArray) {
        private val frequencies = IntArray(3)
        fun solveDutchFlagProblem() {
            if (arr.isEmpty()) return
            countFrequencies()
            fillColorsByFrequencies()
        }

        private fun countFrequencies() {
            for (colorCh in arr) {
                val color = charColorToIndex(colorCh)
                frequencies[color]++
            }
        }

        private fun charColorToIndex(ch: Char): Int {
            return when (ch) {
                'R' -> 0
                'W' -> 1
                else -> 2
            }
        }

        private fun indexColorToChar(ind: Int): Char {
            return when (ind) {
                0 -> 'R'
                1 -> 'W'
                else -> 'B'
            }
        }

        private fun findFirstAvailableFrequencyInd(): Int {
            for (i in frequencies.indices) {
                if (frequencies[i] > 0) return i
            }
            throw IllegalStateException("Frequencies are not available")
        }

        private fun fillColorsByFrequencies() {
            var freqInd = findFirstAvailableFrequencyInd()
            for (i in arr.indices) {
                //if we run out of the current color => find the next available
                if (frequencies[freqInd] <= 0) {
                    freqInd = findFirstAvailableFrequencyInd()
                }
                arr[i] = indexColorToChar(freqInd)
                frequencies[freqInd]--
            }
        }
    }
}


private class Task27_16Test {
    private val task = Task27_16()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: CharArray, expected: CharArray) {
        task.solveDutchFlagProblem(input)
        Assertions.assertArrayEquals(input, expected)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("RWBBWRW".toCharArray(), "RRWWWBB".toCharArray()),
                Arguments.of("".toCharArray(), "".toCharArray()),
                Arguments.of("R".toCharArray(), "R".toCharArray()),
                Arguments.of("W".toCharArray(), "W".toCharArray()),
                Arguments.of("B".toCharArray(), "B".toCharArray()),
                Arguments.of("RW".toCharArray(), "RW".toCharArray()),
                Arguments.of("WR".toCharArray(), "RW".toCharArray()),
                Arguments.of("RWB".toCharArray(), "RWB".toCharArray()),
                Arguments.of("RRRWWBBB".toCharArray(), "RRRWWBBB".toCharArray()),
                Arguments.of("BBBWWRRR".toCharArray(), "RRRWWBBB".toCharArray())
            )
        }
    }
}