package lc1000_1999.lc1200_1299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//From Beyond Cracking The Coding Interview, Solved
//https://leetcode.com/problems/split-a-string-in-balanced-strings/
class Task1221() {
    fun balancedStringSplit(s: String): Int {
        var count = 0
        var heigh = 0
        var openCh = ' '
        for (ch in s) {
            if (heigh == 0) {
                openCh = ch
                heigh++
            } else {
                if (ch == openCh) heigh++ else heigh--
                if (heigh == 0) count++
            }
        }
        return count
    }
}

private class Task1221Test {
    private val task = Task1221()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Int) {
        Assertions.assertEquals(expected, task.balancedStringSplit(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("RLRRLLRLRL", 4),
                Arguments.of("RLRRRLLRLL", 2),
                Arguments.of("LLLLRRRR", 1),
                Arguments.of("RLRRLLRL", 3),
                Arguments.of("RLRRLLLR", 3)
            )
        }
    }
}