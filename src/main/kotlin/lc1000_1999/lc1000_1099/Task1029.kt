package lc1000_1999.lc1000_1099

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.abs

//Similar to Beyond Cracking The Coding Interview, Solved but with a hint
//https://leetcode.com/problems/two-city-scheduling/
class Task1029 {
    fun twoCitySchedCost(costs: Array<IntArray>): Int {
        //sorted by the highest loss
        costs.sortByDescending { abs(it[0] - it[1]) }
        var aCount = costs.size / 2
        var bCount = aCount
        var totalCost = 0
        for (cost in costs) {
            if (bCount == 0 || aCount > 0 && cost[0] < cost[1]) {
                totalCost += cost[0]
                aCount--
            } else {
                totalCost += cost[1]
                bCount--
            }
        }
        return totalCost
    }
}

private class Task1029Test {
    private val task = Task1029()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Int) {
        Assertions.assertEquals(expected, task.twoCitySchedCost(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf(intArrayOf(10, 20), intArrayOf(30, 200), intArrayOf(400, 50), intArrayOf(30, 20)), 110),
                Arguments.of(
                    arrayOf(
                        intArrayOf(259, 770), intArrayOf(448, 54), intArrayOf(926, 667), intArrayOf(184, 139), intArrayOf(840, 118),
                        intArrayOf(577, 469)
                    ), 1159
                ),
                Arguments.of(
                    arrayOf(
                        intArrayOf(515, 563), intArrayOf(451, 713), intArrayOf(537, 709), intArrayOf(343, 819), intArrayOf(855, 779),
                        intArrayOf(457, 60), intArrayOf(650, 359), intArrayOf(631, 42)
                    ), 3086
                )
            )
        }
    }
}