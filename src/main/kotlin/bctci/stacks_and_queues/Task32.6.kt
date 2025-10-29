package bctci.stacks_and_queues

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.text.iterator

class Task32_6() {

    fun countBalancedPartitions(s: String): Int {
        var count = 0
        var heigh = 0
        for (ch in s) {
            if (ch == '(') heigh++ else heigh--
            if (heigh == 0) count++
        }
        return count
    }
}

private class Task32_6Test {
    private val task = Task32_6()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: Int) {
        Assertions.assertEquals(expected, task.countBalancedPartitions(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("((()))(()())()(()(()))", 4),
                Arguments.of("()()()", 3),
                Arguments.of("(((())))", 1),
                Arguments.of("", 0),
                Arguments.of("()", 1)
            )
        }
    }
}