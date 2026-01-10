package lc1000_1999.lc1200_1299

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Solved
//https://leetcode.com/problems/meeting-scheduler/
class Task1229 {
    fun minAvailableDuration(slots1: Array<IntArray>, slots2: Array<IntArray>, duration: Int): List<Int> {
        slots1.sortBy { it[0] }
        slots2.sortBy { it[0] }
        var p1 = 0
        var p2 = 0
        while (p1 < slots1.size && p2 < slots2.size) {
            val int1 = slots1[p1][0]..slots1[p1][1]
            val int2 = slots2[p2][0]..slots2[p2][1]
            //skip invalid intervals
            if (int1.last - int1.first < duration) {
                p1++
                continue
            }
            if (int2.last - int2.first < duration) {
                p2++
                continue
            }

            if (int1.start in int2) {
                val diff = int2.last - int1.start
                if (diff >= duration) {
                    return listOf(int1.start, int1.start + duration)
                }
                p2++
            } else if (int2.start in int1) {
                val diff = int1.last - int2.start
                if (diff >= duration) {
                    return listOf(int2.start, int2.start + duration)
                }
                p1++
            } else if (int2.start > int1.start) {
                p1++
            } else {
                p2++
            }
        }
        return emptyList<Int>()
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