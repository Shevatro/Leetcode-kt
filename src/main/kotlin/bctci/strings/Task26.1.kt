package bctci.strings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.text.iterator

class Task26_1 {
    fun split(s: String, c: Char): List<String> {
        if (s.isEmpty()) return emptyList()
        val result = mutableListOf<String>()
        var sb = StringBuilder()
        for (ch in s) {
            if (ch == c) {
                result.add(sb.toString())
                sb = StringBuilder()
            } else {
                sb.append(ch)
            }
        }
        result.add(sb.toString())
        return result
    }
}


private class Task26_1Test {
    private val task = Task26_1()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: Char, expected: List<String>) {
        Assertions.assertEquals(expected, task.split(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("split by space", ' ', listOf("split", "by", "space")),
                Arguments.of("beekeeper needed", 'e', listOf("b", "", "k", "", "p", "r n", "", "d", "d")),
                Arguments.of("/home/./..//Documents/", '/', listOf("", "home", ".", "..", "", "Documents", "")),
                Arguments.of("", '?', emptyList<String>()),
                Arguments.of("", ' ', emptyList<String>()),
                Arguments.of("", '\n', emptyList<String>()),
                Arguments.of("a", 'a', listOf("", "")),
                Arguments.of("a", 'b', listOf("a")),
                Arguments.of("hello", 'x', listOf("hello")),
                Arguments.of("hello", '?', listOf("hello")),
                Arguments.of("aaa", 'a', listOf("", "", "", "")),
                Arguments.of("\n\n\n", '\n', listOf("", "", "", "")),
                Arguments.of("tab\tseparated\ttext", '\t', listOf("tab", "separated", "text")),
                Arguments.of("one,,two,,,three", ',', listOf("one", "", "two", "", "", "three")),
                Arguments.of(",start,middle,end,", ',', listOf("", "start", "middle", "end", "")),
                Arguments.of(
                    "short,medium string,very very long string", ',',
                    listOf("short", "medium string", "very very long string")
                ),
                Arguments.of("  leading space", ' ', listOf("", "", "leading", "space")),
                Arguments.of("trailing space  ", ' ', listOf("trailing", "space", "", "")),
                Arguments.of("123,456,789", ',', listOf("123", "456", "789")),
                Arguments.of("!@#$%", '@', listOf("!", "#$%"))
            )
        }
    }
}