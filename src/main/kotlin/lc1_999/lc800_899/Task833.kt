package lc1_999.lc800_899
//Solved, but repeat
//https://leetcode.com/problems/find-and-replace-in-string/

class Task833 {
    fun findReplaceString(s: String, indices: IntArray, sources: Array<String>, targets: Array<String>): String {
        var str = s
        for (i in indices.indices) {
            if (isStrsEqual(str, indices[i], sources[i])) {
                val diff = targets[i].length - sources[i].length
                str = replace(str, indices[i], indices[i] + sources[i].length, targets[i])
                updateIndices(indices, i, diff)
            }
        }
        return str
    }

    private fun isStrsEqual(s: String, index: Int, findStr: String): Boolean {
        return s.subSequence(index, index + findStr.length) == findStr
    }

    private fun replace(s: String, startIndex: Int, endIndex: Int, target: String): String {
        return s.subSequence(0, startIndex).toString() + target + s.subSequence(endIndex, s.length)
    }

    private fun updateIndices(indices: IntArray, index: Int, diff: Int) {
        for (i in index + 1 until indices.size) {
            if (indices[i] > indices[index]) indices[i] += diff
        }
    }
}

fun main() {
    val obj = Task833()
    println(obj.findReplaceString("abcd", intArrayOf(0, 2), arrayOf("a", "cd"), arrayOf("eee", "ffff")))
    println(obj.findReplaceString("abcd", intArrayOf(0, 2), arrayOf("ab", "ec"), arrayOf("eee", "ffff")))
    println(
        obj.findReplaceString(
            "vmokgggqzp", intArrayOf(3, 5, 1), arrayOf("kg", "ggq", "mo"), arrayOf("s", "so", "bfr")
        )
    )
}