package bctci.recursion

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task33_1() {

    fun decodeRobotInstructions(s: String): String {
//            return decodeRobotInstructionsRec(s)
        return Solution3(s).decodeRobotInstructions()
    }

    private fun decodeRobotInstructionsRec(subS: String): String {
        if (subS.length == 1 || subS.isEmpty()) return subS
        return if (subS.first() == '2') {
            decodeRobotInstructionsRec(subS.drop(1)) + decodeRobotInstructionsRec(subS.drop(2))
        } else {
            subS.first() + decodeRobotInstructionsRec(subS.drop(1))
        }
    }

    class Solution2(s: String) {
        private val arr = s.toCharArray()
        private val endPos = arr.lastIndex
        fun decodeRobotInstructions(): String {
            return decodeRobotInstructionsRec(0)
        }

        private fun decodeRobotInstructionsRec(startPos: Int): String {
            if (startPos > endPos) return ""
            if (startPos == endPos) return arr[startPos].toString()
            return if (arr[startPos] == '2') {
                decodeRobotInstructionsRec(startPos + 1) + decodeRobotInstructionsRec(startPos + 2)
            } else {
                arr[startPos] + decodeRobotInstructionsRec(startPos + 1)
            }
        }
    }

    class Solution3(s: String) {
        private val arr = s.toCharArray()
        private val endPos = arr.lastIndex
        private val cache = mutableMapOf<Int, String>()
        fun decodeRobotInstructions(): String {
            return decodeRobotInstructionsRec(0)
        }

        private fun decodeRobotInstructionsRec(startPos: Int): String {
            if (cache[startPos]!=null) return cache[startPos]!!
            if (startPos > endPos) return ""
            if (startPos == endPos) return arr[startPos].toString()
            cache[startPos]= if (arr[startPos] == '2') {
                decodeRobotInstructionsRec(startPos + 1) + decodeRobotInstructionsRec(startPos + 2)
            } else {
                arr[startPos] + decodeRobotInstructionsRec(startPos + 1)
            }
            return cache[startPos]!!
        }
    }
}

private class Task33_1Test {
    private val task = Task33_1()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: String) {
        Assertions.assertEquals(expected, task.decodeRobotInstructions(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("LL", "LL"),
                Arguments.of("2LR", "LRR"),
                Arguments.of("2L", "L"),
                Arguments.of("22LR", "LRRLR"),
                Arguments.of("LL2R2L", "LLRLL"),
                Arguments.of("", ""),
                Arguments.of("L", "L"),
                Arguments.of("2222LR", "LRRLRLRRLRRLR"),
                Arguments.of("LR", "LR")
            )
        }
    }
}