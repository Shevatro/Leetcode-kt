fun IntArray.toString2(): String {
    return joinToString(prefix = "[", postfix = "]", separator = " ")
}