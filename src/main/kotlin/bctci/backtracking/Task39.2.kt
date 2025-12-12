package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task39_2 {
    fun calcSubsetEnumeration(s: List<Char>): Set<List<Char>> {
        return Solution(s).calcSubsetEnumeration()
    }

    private class Solution(private val s: List<Char>) {
        private val result = mutableSetOf<List<Char>>()
        private val tempSet = mutableSetOf<Char>()
        fun calcSubsetEnumeration(): Set<List<Char>> {
            dp(0)
            return result
        }

        private fun dp(i: Int) {
            if (i == s.size) {
                result.add(tempSet.toList())
                return
            }
            //choice1: pick s[i]
            tempSet.add(s[i])
            dp(i + 1)
           //undo
            tempSet.remove(s[i])
            //choice2: skip s[i]
            dp(i + 1)
        }
    }
}


private class Task39_2Test {
    private val task = Task39_2()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: List<Char>, expected: Set<List<Char>>) {
        Assertions.assertEquals(expected, task.calcSubsetEnumeration(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf('x', 'y', 'z'),
                    setOf(
                        emptyList(),
                        listOf('x'), listOf('y'), listOf('z'),
                        listOf('x', 'y'), listOf('x', 'z'), listOf('y', 'z'),
                        listOf('x', 'y', 'z')
                    )
                ),
                Arguments.of(listOf<Char>(), setOf(emptyList<Char>())),
                Arguments.of(listOf('a'), setOf(emptyList(), listOf('a'))),
                Arguments.of(listOf('a', 'b'), setOf(emptyList(), listOf('a'), listOf('b'), listOf('a', 'b'))),
                Arguments.of(
                    listOf('a', 'b', 'c', 'd'),
                    setOf(
                        emptyList(),
                        listOf('a'), listOf('b'), listOf('c'), listOf('d'),
                        listOf('a', 'b'), listOf('a', 'c'), listOf('a', 'd'),
                        listOf('b', 'c'), listOf('b', 'd'), listOf('c', 'd'),
                        listOf('a', 'b', 'c'), listOf('a', 'b', 'd'), listOf('a', 'c', 'd'), listOf('b', 'c', 'd'),
                        listOf('a', 'b', 'c', 'd')
                    )
                )
            )
        }
    }
}