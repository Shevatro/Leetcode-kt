package lc1000_1999.lc1700_1799

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

//Solved
//https://leetcode.com/problems/average-waiting-time/
class Task1701() {
    fun averageWaitingTime(customers: Array<IntArray>): Double {
        var sum = 0L
        var chefTime = 0
        for (customer in customers) {
            chefTime = max(chefTime, customer[0])
            chefTime += customer[1]
            sum += (chefTime - customer[0])
        }
        return sum / customers.size.toDouble()
    }
}

private class Task5Test {
    private val task = Task1701()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: Array<IntArray>, expected: Double) {
        Assertions.assertEquals(expected, task.averageWaitingTime(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf(intArrayOf(1, 2), intArrayOf(2, 5), intArrayOf(4, 3)), 5.0
                ),
                Arguments.of(
                    arrayOf(intArrayOf(5, 2), intArrayOf(5, 4), intArrayOf(10, 3), intArrayOf(20, 1)), 3.25
                ),
                Arguments.of(arrayOf(intArrayOf(1, 1)), 1),
                Arguments.of(
                    arrayOf(
                        intArrayOf(1, 496), intArrayOf(2, 8112), intArrayOf(6, 1395), intArrayOf(7, 7068),
                        intArrayOf(7, 9388), intArrayOf(8, 905), intArrayOf(8, 8590), intArrayOf(8, 5521),
                        intArrayOf(8, 6158), intArrayOf(10, 8466), intArrayOf(11, 2337), intArrayOf(12, 1220),
                        intArrayOf(12, 3716), intArrayOf(13, 7951), intArrayOf(14, 6366), intArrayOf(14, 4162),
                        intArrayOf(14, 7002), intArrayOf(15, 2950), intArrayOf(17, 6518), intArrayOf(17, 7449),
                        intArrayOf(18, 4937), intArrayOf(19, 1821), intArrayOf(20, 8373), intArrayOf(22, 1358),
                        intArrayOf(23, 4917), intArrayOf(24, 9426), intArrayOf(26, 2589), intArrayOf(27, 7284),
                        intArrayOf(30, 7260), intArrayOf(30, 6943), intArrayOf(31, 4695), intArrayOf(32, 7456),
                        intArrayOf(32, 3251), intArrayOf(33, 278), intArrayOf(34, 9565), intArrayOf(35, 473),
                        intArrayOf(36, 4857), intArrayOf(37, 7986), intArrayOf(37, 2035), intArrayOf(38, 1502),
                        intArrayOf(38, 4237), intArrayOf(41, 4175), intArrayOf(42, 7837), intArrayOf(42, 5949),
                        intArrayOf(43, 4614), intArrayOf(46, 3962), intArrayOf(46, 3226), intArrayOf(48, 2093),
                        intArrayOf(49, 1049), intArrayOf(50, 2192), intArrayOf(51, 3349), intArrayOf(52, 4579),
                        intArrayOf(53, 4406), intArrayOf(53, 9947), intArrayOf(57, 7838), intArrayOf(58, 214),
                        intArrayOf(61, 8615), intArrayOf(62, 5541), intArrayOf(63, 8839), intArrayOf(63, 3873),
                        intArrayOf(64, 1655), intArrayOf(64, 2849), intArrayOf(64, 1208), intArrayOf(65, 1205),
                        intArrayOf(65, 5235), intArrayOf(66, 196), intArrayOf(66, 7359), intArrayOf(67, 7971),
                        intArrayOf(68, 1018), intArrayOf(68, 8263), intArrayOf(69, 1880), intArrayOf(70, 8755),
                        intArrayOf(71, 8382), intArrayOf(74, 6371), intArrayOf(74, 9326), intArrayOf(75, 8945),
                        intArrayOf(77, 2827), intArrayOf(77, 4006), intArrayOf(77, 1425), intArrayOf(79, 5272),
                        intArrayOf(80, 8213), intArrayOf(81, 2), intArrayOf(81, 7784), intArrayOf(83, 5064),
                        intArrayOf(84, 3824), intArrayOf(84, 275), intArrayOf(85, 1066), intArrayOf(86, 4072),
                        intArrayOf(87, 804), intArrayOf(89, 4134), intArrayOf(89, 2180), intArrayOf(90, 4863),
                        intArrayOf(91, 5574), intArrayOf(91, 392), intArrayOf(91, 6700), intArrayOf(91, 8220),
                        intArrayOf(92, 8623), intArrayOf(92, 7956), intArrayOf(93, 9683), intArrayOf(93, 1060),
                        intArrayOf(93, 3749), intArrayOf(94, 5498), intArrayOf(94, 6493), intArrayOf(94, 8234),
                        intArrayOf(96, 2028), intArrayOf(96, 6512), intArrayOf(97, 5006), intArrayOf(98, 9340),
                        intArrayOf(99, 9640), intArrayOf(99, 9354), intArrayOf(101, 6443), intArrayOf(102, 8702),
                        intArrayOf(102, 9630), intArrayOf(103, 6176), intArrayOf(103, 2541), intArrayOf(104, 8846),
                        intArrayOf(104, 6027), intArrayOf(106, 9286), intArrayOf(107, 9531), intArrayOf(108, 1007),
                        intArrayOf(109, 309), intArrayOf(109, 6117), intArrayOf(109, 7233), intArrayOf(112, 395),
                        intArrayOf(115, 2995), intArrayOf(116, 8367), intArrayOf(117, 9077), intArrayOf(120, 3897),
                        intArrayOf(120, 8604), intArrayOf(122, 9613), intArrayOf(123, 7676), intArrayOf(125, 7897),
                        intArrayOf(125, 1876), intArrayOf(125, 6479), intArrayOf(126, 3836), intArrayOf(129, 8801),
                    ), 341808.8161764706
                )
            )
        }
    }
}