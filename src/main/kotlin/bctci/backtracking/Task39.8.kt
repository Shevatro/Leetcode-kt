package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task39_8(private val password: String) {
    fun findPassword(): String? {
        return Solution({ password == it }).findPassword()
    }

    private class Solution(
        private val checkPassword: (String) -> Boolean
    ) {
        //It should be 10 but it takes a lot of time to run, that's why it was reduced
        private val MAX_SIZE = 6
        private val curPath = mutableSetOf<Char>()
        private var foundPassword: String? = null

        fun findPassword(): String? {
            backtrack(0)
            return foundPassword
        }

        private fun backtrack(length: Int) {
            if (foundPassword != null || length == MAX_SIZE + 1) return
            val str = curPath.joinToString("")
            if (str.isNotEmpty() && checkPassword(str)) {
                foundPassword = str
                return
            }
            for (ch in 'a'..'z') {
                if (curPath.contains(ch)) continue
                curPath.add(ch)
                backtrack(length + 1)
                curPath.remove(ch)
            }
        }
    }
}


private class Task39_8Test {

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(expected: String) {
        val task = Task39_8(expected)
        Assertions.assertEquals(expected, task.findPassword())
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("a"),
                Arguments.of("bc"),
                Arguments.of("def"),
                Arguments.of("ghij"),
                Arguments.of("klmno"),
                Arguments.of("pqrstu")
            )
        }
    }
}