package common

class UnionFind(size: Int) {
    private val arr = IntArray(size)
    private val rankArr = IntArray(size)
    private var rootCount = size

    init {
        for (i in arr.indices) {
            arr[i] = i
            rankArr[i] = 1
        }
    }

    fun find(item: Int): Int {
        if (item == arr[item]) return item
        arr[item] = find(arr[item])
        return arr[item]
    }

    fun union(root: Int, item: Int) {
        val rootRoot = find(root)
        val rootItem = find(item)
        if (rootRoot != rootItem) {
            if (rankArr[rootRoot] >= rankArr[rootItem]) {
                arr[rootItem] = rootRoot
                if (rankArr[rootRoot] == rankArr[rootItem]) rankArr[rootRoot]++
            } else {
                arr[rootRoot] = rootItem
            }
            rootCount--
        }
    }

    fun countRoots(): Int {
        var count = 0
        for (i in arr.indices) {
            if (i == arr[i]) count++
        }
        return count
    }

    fun getAmountRoots() = rootCount

    fun isConnected(item1: Int, item2: Int): Boolean {
        return find(item1) == find(item2)
    }
}