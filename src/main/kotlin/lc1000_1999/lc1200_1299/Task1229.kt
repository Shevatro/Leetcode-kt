package lc1000_1999.lc1200_1299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

//Solved
//https://leetcode.com/problems/meeting-scheduler/
class Task1229 {
    fun minAvailableDuration(slots1: Array<IntArray>, slots2: Array<IntArray>, duration: Int): List<Int> {
        slots1.sortBy { it[0] }
        slots2.sortBy { it[0] }
        var p1 = 0
        var p2 = 0
        while (p1 < slots1.size && p2 < slots2.size) {
            val intersectStart = max(slots1[p1][0], slots2[p2][0])
            val intersectEnd = min(slots1[p1][1], slots2[p2][1])
            if (intersectEnd - intersectStart >= duration) {
                return listOf(intersectStart, intersectStart + duration)
            }
            //move who finishes earlier
            if (slots2[p2][1] > slots1[p1][1]) {
                p1++
            } else {
                p2++
            }
        }
        return emptyList()
    }
}

private class Task1229Test {
    private val task = Task1229()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Array<IntArray>, input2: Array<IntArray>, input3: Int, expected: List<Int>) {
        Assertions.assertEquals(expected, task.minAvailableDuration(input1, input2, input3))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(intArrayOf(10, 50), intArrayOf(60, 120), intArrayOf(140, 210)),
                    arrayOf(intArrayOf(0, 15), intArrayOf(60, 70)), 8, listOf(60, 68)
                ),
                Arguments.of(
                    arrayOf(intArrayOf(10, 50), intArrayOf(60, 120), intArrayOf(140, 210)),
                    arrayOf(intArrayOf(0, 15), intArrayOf(60, 70)), 12, emptyList<Int>()
                ),
                Arguments.of(
                    arrayOf(intArrayOf(0, 15), intArrayOf(60, 70)),
                    arrayOf(intArrayOf(10, 50), intArrayOf(60, 120), intArrayOf(140, 210)), 8, listOf(60, 68)
                ),
                Arguments.of(
                    arrayOf(intArrayOf(0, 9), intArrayOf(60, 70)),
                    arrayOf(intArrayOf(10, 50), intArrayOf(60, 120), intArrayOf(140, 210)), 8, listOf(60, 68)
                ),
                Arguments.of(
                    arrayOf(intArrayOf(10, 50), intArrayOf(60, 120), intArrayOf(140, 210)),
                    arrayOf(intArrayOf(0, 9), intArrayOf(60, 70)), 8, listOf(60, 68)
                ),
                Arguments.of(
                    arrayOf(intArrayOf(10, 60)),
                    arrayOf(intArrayOf(12, 17), intArrayOf(21, 50)), 8, listOf(21, 29)
                ),
                Arguments.of(
                    arrayOf(intArrayOf(10, 50), intArrayOf(60, 120), intArrayOf(140, 210)),
                    arrayOf(intArrayOf(0, 15), intArrayOf(40, 50)), 8, listOf(40, 48)
                ),
                Arguments.of(
                    arrayOf(
                        intArrayOf(216397070, 363167701), intArrayOf(98730764, 158208909), intArrayOf(441003187, 466254040),
                        intArrayOf(558239978, 678368334), intArrayOf(683942980, 717766451)
                    ),
                    arrayOf(
                        intArrayOf(50490609, 222653186), intArrayOf(512711631, 670791418), intArrayOf(730229023, 802410205),
                        intArrayOf(812553104, 891266775), intArrayOf(230032010, 399152578)
                    ),
                    456085, listOf(98730764, 99186849)
                )
            )
        }
    }
}