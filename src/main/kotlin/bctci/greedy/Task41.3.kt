package bctci.greedy

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.sqrt

class Task41_3 {

    fun minDistanceToCenters(points: Array<DoubleArray>, center1: DoubleArray, center2: DoubleArray): Double {
        // Sort by how much better center1 is than center2 (squared distances)
        //Note: we don't need a square root in sorting (it's possible but redundant)
        points.sortBy { dist(it, center1) - dist(it, center2) }
        val half = points.size / 2
        var totalDistance = 0.0
        for (i in 0 until half) {
            totalDistance += sqrt(dist(points[i], center1))
            totalDistance += sqrt(dist(points[i + half], center2))
        }
        return totalDistance
    }

    private fun dist(p1: DoubleArray, p2: DoubleArray): Double {
        val dx = p1[0] - p2[0]
        val dy = p1[1] - p2[1]
        return dx * dx + dy * dy
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