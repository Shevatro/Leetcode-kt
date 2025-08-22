package lc1_999.lc1_99

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

//Not Solved, Incorrect
//https://leetcode.com/problems/longest-palindromic-substring
class Task5() {
    fun longestPalindrome(s: String): String {
        return Solution(s).longestPalindrome()
    }

//    private class Solution2(private val s: String){
//        private val cache = mutableMapOf<String, Boolean>()
//        fun longestPalindrome(): String {
//
//        }
//
//        private fun recur(s: String): Boolean{
//            when (s.length){
//                1-> return true
//                2, 3-> {
//                    if (cache[s]!=null) return cache[s]!!
//                    val result = s.last() == s.first()
//                    cache[s] = result
//                    return result
//                }
//                else ->{
//                    val st1 = s.substring(0..<s.length-1)
//                    val st2 = s.substring(1..<s.length)
//                    if (cache[st1] ==null){
//                        cache[st1] = recur(st1)
//                    }
//                    if (cache[st2] ==null){
//                        cache[st2] = recur(st2)
//                    }
//
//                    if ()
//                }
//            }
//            if (s.length == 1) return true
//            if (s.length == 2 || s.length == 3) return s.last() == s.first()
//
//
//        }
//    }

    private class Solution(private val s: String) {
        private val options = mutableSetOf<String>()

        fun longestPalindrome(): String {
            if (s.length == 1) return s
            findPossibleOptions()
            println(options)
            validate()
            println(options)
            return options.firstOrNull() ?: s.first().toString()
        }

        private fun validate() {
            val iterator = options.iterator()
            while (iterator.hasNext()) {
                val arr = iterator.next().toCharArray()
                var i = 0
                var j = arr.lastIndex
                while (i < j) {
                    if (arr[i] == arr[j]) {
                        i++
                        j--
                    } else {
                        iterator.remove()
                        break
                    }
                }
            }
        }

        private fun findPossibleOptions() {
            val visited = mutableSetOf<String>()
            val queue = ArrayDeque<String>()
            queue.addLast(s)
            while (queue.isNotEmpty()) {
                val item = queue.removeFirst()
                if (visited.contains(item)) continue
                visited.add(item)
                if (item.length < 2) continue
                if (item.first() == item.last()) {
                    options.add(item)
                }
                if (item.length <= 2) continue
                queue.addLast(item.substring(0..<item.length - 1))
                queue.addLast(item.substring(1..<item.length))
            }
        }
    }
}

private class Task5Test {
    private val task = Task5()

    @ParameterizedTest
    @MethodSource("inputDataProvider")
    fun test(input: String, expected: String) {
        Assertions.assertEquals(expected, task.longestPalindrome(input))
    }

    companion object {
        @JvmStatic
        private fun inputDataProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("babad", "bab"),
                Arguments.of("cbbd", "bb"),
                Arguments.of("xabax", "xabax"),
                Arguments.of("a", "a"),
                Arguments.of("ac", "a"),
                Arguments.of("aacabdkacaa", "aca"),
                Arguments.of("kztakrekvefgchersuoiuatzlmwynzjhdqqftjcqmntoyckqfawikkdrnfgbwtdpbkymvwoumurjdzygyzsbmwzpcxcdmmpwzmeibligwiiqbecxwyxigikoewwrczkanwwqukszsbjukzumzladrvjefpegyicsgctdvldetuegxwihdtitqrdmygdrsweahfrepdcudvyvrggbkthztxwicyzazjyeztytwiyybqdsczozvtegodacdokczfmwqfmyuixbeeqluqcqwxpyrkpfcdosttzooykpvdykfxulttvvwnzftndvhsvpgrgdzsvfxdtzztdiswgwxzvbpsjlizlfrlgvlnwbjwbujafjaedivvgnbgwcdbzbdbprqrflfhahsvlcekeyqueyxjfetkxpapbeejoxwxlgepmxzowldsmqllpzeymakcshfzkvyykwljeltutdmrhxcbzizihzinywggzjctzasvefcxmhnusdvlderconvaisaetcdldeveeemhugipfzbhrwidcjpfrumshbdofchpgcsbkvaexfmenpsuodatxjavoszcitjewflejjmsuvyuyrkumednsfkbgvbqxfphfqeqozcnabmtedffvzwbgbzbfydiyaevoqtfmzxaujdydtjftapkpdhnbmrylcibzuqqynvnsihmyxdcrfftkuoymzoxpnashaderlosnkxbhamkkxfhwjsyehkmblhppbyspmcwuoguptliashefdklokjpggfiixozsrlwmeksmzdcvipgkwxwynzsvxnqtchgwwadqybkguscfyrbyxudzrxacoplmcqcsmkraimfwbauvytkxdnglwfuvehpxd", "dtzztd"),
            )
        }
    }
}