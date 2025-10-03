package lc1_999.lc200_299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

// Solved but with a hint (tricky)
//https://leetcode.com/problems/find-the-celebrity/description/
class Task277() {
    fun findCelebrity(graph: Array<IntArray>): Int {
        return Solution(graph).findCelebrity()
    }

    class Solution(graph: Array<IntArray>) : Relation(graph) {
        private val n = graph.size
        private var candidate: Int = -1
        fun findCelebrity(): Int {
            candidate = findCandidate()
            return if (validateCandidate()) candidate else -1
        }

        fun findCelebrityAlternative(): Int {
            candidate = findCandidate()

            val availableCelebrities = (0..n - 1).toMutableSet()
            availableCelebrities.remove(candidate)
            val isCelebrity = availableCelebrities.all { knows(it, candidate) && !knows(candidate, it) }
            if (!isCelebrity) return -1
            return candidate
        }

        private fun findCandidate(): Int {
            var indexA = 0
            var indexB = 1
            while (indexA < n && indexB < n) {
                val aKnowsB = knows(indexA, indexB)
                if (aKnowsB) indexA = indexB
                indexB++
            }
            return indexA
        }

        private fun validateCandidate(): Boolean {
            for (i in 0 until n) {
                if (i == candidate) continue
                if (!knows(i, candidate) || knows(candidate, i)) {
                    return false
                }
            }
            return true
        }
    }

    open class Relation(private val data: Array<IntArray>) {
        fun knows(a: Int, b: Int) = data[a][b] == 1
    }
}

private class Task277Test {
    private val task = Task277()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(graph: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.findCelebrity(graph))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(1, 1, 0), intArrayOf(0, 1, 0), intArrayOf(1, 1, 1)), 1),
                Arguments.of(arrayOf(intArrayOf(1, 0, 1), intArrayOf(1, 1, 0), intArrayOf(0, 1, 1)), -1),
                Arguments.of(arrayOf(intArrayOf(1, 1), intArrayOf(1, 1)), -1)
            )
        }
    }
}