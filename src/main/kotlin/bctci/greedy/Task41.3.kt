package bctci.greedy

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.sqrt

class Task41_3 {

    fun minDistanceToCenters(points: Array<DoubleArray>, center1: DoubleArray, center2: DoubleArray): Double {
        points.sortBy {
            sqrt(((it[0] - center1[0]) * (it[0] - center1[0])) + ((it[1] - center1[1]) * (it[1] - center1[1]))) -
                    sqrt(((it[0] - center2[0]) * (it[0] - center2[0])) + ((it[1] - center2[1]) * (it[1] - center2[1])))
        }
        val half = points.size / 2
        var totalDistance = 0.0
        for (i in 0 until half) {
            totalDistance += sqrt(((points[i][0] - center1[0]) * (points[i][0] - center1[0])) + ((points[i][1] - center1[1]) * (points[i][1] - center1[1])))
            totalDistance += sqrt(((points[i + half][0] - center2[0]) * (points[i + half][0] - center2[0])) + ((points[i + half][1] - center2[1]) * (points[i + half][1] - center2[1])))
        }
        return totalDistance
    }
}


private class Task41_3Test {
    private val task = Task41_3()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: Array<DoubleArray>, input2: DoubleArray, input3: DoubleArray, expected: Double) {
        val tolerance = 0.001 // Acceptable margin of error
        Assertions.assertEquals(expected, task.minDistanceToCenters(input1, input2, input3), tolerance)
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {

            return Stream.of(
                Arguments.of(
                    arrayOf(
                        doubleArrayOf(0.0, 1.0), doubleArrayOf(1.0, 0.0), doubleArrayOf(-1.0, 0.0), doubleArrayOf(0.0, -1.0)
                    ), doubleArrayOf(0.0, 0.0), doubleArrayOf(1.0, 1.0), 4.0
                ),
                Arguments.of(
                    arrayOf(doubleArrayOf(0.0, 0.0), doubleArrayOf(0.0, 0.0)), doubleArrayOf(0.0, 0.0), doubleArrayOf(1.0, 1.0),
                    1.414
                ),
                Arguments.of(
                    arrayOf(doubleArrayOf(0.0, 0.5), doubleArrayOf(1.0, 0.5)), doubleArrayOf(0.0, 0.0), doubleArrayOf(1.0, 1.0),
                    1.0
                ),
                Arguments.of(emptyArray<DoubleArray>(), doubleArrayOf(0.3, -3.3), doubleArrayOf(-1.6, 4.6), 0.0),
                Arguments.of(
                    arrayOf(doubleArrayOf(0.0, 0.0), doubleArrayOf(3.0, 0.0)), doubleArrayOf(2.0, 0.0), doubleArrayOf(5.0, 0.0),
                    4.0
                ),
                Arguments.of(
                    arrayOf(doubleArrayOf(0.0, 0.0), doubleArrayOf(0.0, 0.0), doubleArrayOf(0.0, 0.0), doubleArrayOf(0.0, 0.0)),
                    doubleArrayOf(0.0, 0.0), doubleArrayOf(0.0, 0.0),
                    0.0
                ),
            )
        }
    }
}