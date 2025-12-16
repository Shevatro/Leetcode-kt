package bctci.backtracking

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Task39_5 {
    fun getThesaurusly(sentence: String, synonyms: Map<String, List<String>>): Set<String> {
        return Solution(sentence, synonyms).getThesaurusly()
    }

    private class Solution(
        sentence: String,
        private val synonyms: Map<String, List<String>>
    ) {
        private val keys = synonyms.keys.toList()
        private val result = mutableSetOf<String>()
        private val current = sentence.split(" ").toMutableList()

        fun getThesaurusly(): Set<String> {
            backtrack(0)
            return result
        }

        private fun backtrack(i: Int) {
            if (i == keys.size) {
                result.add(current.joinToString(" "))
                return
            }
            val original = keys[i]
            val index = current.indexOf(original)
            for (synonym in synonyms[original]!!) {
                current[index] = synonym
                backtrack(i + 1)
                current[index] = original
            }
        }
    }
}


private class Task39_5Test {
    private val task = Task39_5()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input1: String, input2: Map<String, List<String>>, expected: Set<String>) {
        Assertions.assertEquals(expected, task.getThesaurusly(input1, input2))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "one does not simply walk into mordor",
                    mapOf(
                        "walk" to listOf("stroll", "hike", "wander"),
                        "simply" to listOf("just", "merely")
                    ),
                    setOf(
                        "one does not just stroll into mordor",
                        "one does not just hike into mordor",
                        "one does not just wander into mordor",
                        "one does not merely stroll into mordor",
                        "one does not merely hike into mordor",
                        "one does not merely wander into mordor"
                    )
                ),
                Arguments.of("walk", mapOf("walk" to listOf("stroll")), setOf("stroll")),
                Arguments.of("walk", mapOf("walk" to listOf("stroll", "hike")), setOf("stroll", "hike")),
                Arguments.of("hello world", emptyMap<String, List<String>>(), setOf("hello world")),
                Arguments.of(
                    "I walk to the park",
                    mapOf("walk" to listOf("stroll", "hike")),
                    setOf("I stroll to the park", "I hike to the park")
                )
            )
        }
    }
}