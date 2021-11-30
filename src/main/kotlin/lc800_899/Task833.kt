package lc800_899

class Task833 {
    //Wrong
    fun findReplaceString(s: String, indices: IntArray, sources: Array<String>, targets: Array<String>): String {
        val chars = mutableListOf<Char>()
        var i = 0
        var j = 0
        while (i < s.length) {
            if (i == indices.getOrNull(j)) {
                val size = sources[j].length
                val s1 = s.subSequence(i, i + size)
                val s2 = sources[j]
                if (s1 == s2) {
                    chars.addAll(targets[j].toList())
                    i += size
                } else {
                    chars.addAll(s1.toList())
                    i += s1.length
                }
                j++
            } else {
                chars.add(s[i])
                i++
            }
        }
        return String(chars.toCharArray())
    }
}

fun main() {
    val obj = Task833()
    println(obj.findReplaceString("abcd", intArrayOf(0, 2), arrayOf("a", "cd"), arrayOf("eee", "ffff")))
    println(obj.findReplaceString("abcd", intArrayOf(0, 2), arrayOf("ab", "ec"), arrayOf("eee", "ffff")))
    println(obj.findReplaceString("vmokgggqzp", intArrayOf(3, 5, 1), arrayOf("kg","ggq","mo"), arrayOf("s","so","bfr")))
}