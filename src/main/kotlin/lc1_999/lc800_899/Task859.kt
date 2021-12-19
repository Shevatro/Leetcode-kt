package lc1_999.lc800_899

//Not solved, repeat
//https://leetcode.com/problems/buddy-strings/
class Task859 {
    fun buddyStrings(s: String, goal: String): Boolean {
        if (s.length != goal.length) return false
        if (s == goal) {
            val counts = IntArray(26)
            for (i in s.indices) {
                counts[s[i] - 'a']++
            }
            for (count in counts) {
                if (count > 1) return true
            }
            return false
        }
        val chsNotExist: MutableList<Pair<Char, Char>> = mutableListOf()
        for (i in s.indices) {
            if (s[i] != goal[i]) chsNotExist.add(s[i] to goal[i])
        }
        if (chsNotExist.size != 2) return false
        return chsNotExist[0].first == chsNotExist[1].second && chsNotExist[0].second == chsNotExist[1].first
    }
}

fun main() {
    println(Task859().buddyStrings("ab", "ba"))
    println(Task859().buddyStrings("ab", "ab"))
    println(Task859().buddyStrings("aa", "aa"))
    println(Task859().buddyStrings("aaaaaaabc", "aaaaaaacb"))
}