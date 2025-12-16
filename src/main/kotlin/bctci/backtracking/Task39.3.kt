package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task39_3 {
    fun calcPermutationEnumeration(s: List<Char>): Set<List<Char>> {
        return Solution2(s).calcPermutationEnumeration()
//        return Solution(s).calcPermutationEnumeration()
    }

    private class Solution(private val s: List<Char>) {
        private val result = mutableSetOf<List<Char>>()
        private val curSubSet = mutableSetOf<Char>()
        fun calcPermutationEnumeration(): Set<List<Char>> {
            backtrack()
            return result
        }

        private fun backtrack() {
            if (curSubSet.size == s.size) {
                result.add(curSubSet.toList())
                return
            }
            for (ch in s) {
                if (!curSubSet.contains(ch)) {
                    curSubSet.add(ch)
                    backtrack()
                    curSubSet.remove(ch)
                }
            }
        }
    }

    private class Solution2(private val s: List<Char>) {
        private val result = mutableSetOf<List<Char>>()
        private val current = s.toCharArray()
        fun calcPermutationEnumeration(): Set<List<Char>> {
            backtrack(0)
            return result
        }

        private fun backtrack(i: Int) {
            if (i == s.size) {
                result.add(current.toList())
                return
            }
            for (j in i until s.size) {
                swap(i, j)
                backtrack(i + 1)
                swap(i, j)
            }
        }

        private fun swap(i: Int, j: Int) {
            val temp = current[i]
            current[i] = current[j]
            current[j] = temp
        }
    }
}


private class Task39_3Test {
    private val task = Task39_3()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: List<Char>, expected: Set<List<Char>>) {
        Assertions.assertEquals(expected, task.calcPermutationEnumeration(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf('x', 'y', 'z'),
                    setOf(
                        listOf('x', 'y', 'z'), listOf('x', 'z', 'y'), listOf('y', 'x', 'z'),
                        listOf('y', 'z', 'x'), listOf('z', 'x', 'y'), listOf('z', 'y', 'x')
                    )
                ),
                Arguments.of(listOf('a'), setOf(listOf('a'))),
                Arguments.of(listOf('a', 'b'), setOf(listOf('a', 'b'), listOf('b', 'a'))),
                Arguments.of(
                    listOf('a', 'b', 'c'),
                    setOf(
                        listOf('a', 'b', 'c'), listOf('a', 'c', 'b'), listOf('b', 'a', 'c'),
                        listOf('b', 'c', 'a'), listOf('c', 'a', 'b'), listOf('c', 'b', 'a')
                    )
                )
            )
        }
    }
}