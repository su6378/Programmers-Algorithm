fun main() {
    SolutionKotlin().solution(
            key = arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 0, 0), intArrayOf(0, 1, 1)),
            lock = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1)))
}

class SolutionKotlin {
    var n = 0
    var m = 0
    var move = 0

    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        n = lock.size
        m = key.size
        move = m - 1

        for (x in 0 until move + n) {
            for (y in 0 until move + n) {
                for (r in 0..3) {
                    val map = Array(n + m * 2) { IntArray(n + m * 2) }

                    for (i in 0 until n) {
                        for (j in 0 until n) {
                            map[i + move][j + move] = lock[i][j]
                        }
                    }
                    rotate(map, key, r, x, y)
                    if (check(map)) return true
                }
            }
        }

        return false
    }

    fun rotate(map: Array<IntArray>, key: Array<IntArray>, degree: Int, x: Int, y: Int) {
        for (i in 0 until m) {
            for (j in 0 until m) {
                when (degree) {
                    0 -> map[y + i][x + j] += key[i][j]
                    1 -> map[y + i][x + j] += key[j][m - i - 1]
                    2 -> map[y + i][x + j] += key[m - i - 1][m - j - 1]
                    3 -> map[y + i][x + j] += key[m - j - 1][i]
                }
            }
        }
    }

    fun check(map: Array<IntArray>): Boolean {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (map[move + i][move + j] != 1) return false
            }
        }

        return true
    }
}