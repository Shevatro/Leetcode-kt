package bctci.strings

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task26_2 {
    fun join(arr: Array<String>, s: String): String {
        val sb = StringBuilder()
        for (i in arr.indices) {
            sb.append(arr[i])
            if (i != arr.lastIndex) sb.append(s)
        }
        return sb.toString()
    }
}


private class Task26_2Test {
    private val task = Task26_2()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Array<String>, input2: String, expected: String) {
        Assertions.assertEquals(expected, task.join(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf("join", "by", "space"), " ", "join by space"),
                Arguments.of(arrayOf("b", "", "k", "", "p", "r n", "", "d", "d!!"), "ee", "beeeekeeeepeer neeeedeed!!"),
                Arguments.of(emptyArray<String>(), "x", ""),
                Arguments.of(arrayOf<String>(), "", ""),
                Arguments.of(arrayOf<String>(), "long separator", ""),

                // Edge case - single element arrays
                Arguments.of(arrayOf("a"), "x", "a"),
                Arguments.of(arrayOf(""), "x", ""),
                Arguments.of(arrayOf("multiple words"), "x", "multiple words"),

                // Two element arrays
                Arguments.of(arrayOf("a", "b"), "", "ab"),
                Arguments.of(arrayOf("a", "b"), " ", "a b"),
                Arguments.of(arrayOf("", ""), ",", ","),

                // Edge case - empty strings in array
                Arguments.of(arrayOf("", "", ""), ",", ",,"),
                Arguments.of(arrayOf("hello", "", "world"), " ", "hello  world"),

                // Special characters
                Arguments.of(arrayOf("\n", "\t"), ",", "\n,\t"),
                Arguments.of(arrayOf("tab", "separated"), "\t", "tab\tseparated"),

                // Long separators
                Arguments.of(arrayOf("short", "strings"), "very long separator", "shortvery long separatorstrings"),

                // Mixed content
                Arguments.of(arrayOf("123", "abc", "!@#", "   "), "|", "123|abc|!@#|   "),

                // Whitespace handling
                Arguments.of(arrayOf("  leading", "trailing  ", "  both  "), "|", "  leading|trailing  |  both  "),

                // Numbers and special chars
                Arguments.of(arrayOf("123", "456"), "-", "123-456"),
                Arguments.of(arrayOf("!@#", "$%^"), "&", "!@#&$%^")
            )
        }
    }
}