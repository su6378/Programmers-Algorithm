import kotlin.math.max

fun main() {
    SolutionKotlin().solution(stones = intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1), k = 3)
}

class SolutionKotlin {
    fun solution(stones: IntArray, k: Int): Int {
        var answer = 0
        var min = 1
        var max = 200000000

        while (min <= max) {
            var mid = (min + max) / 2

            if (canCross(k, stones, mid)) {
                min = mid + 1
                answer = max(answer, mid)
            } else max = mid - 1
        }

        return answer
    }

    fun canCross(k: Int, stones: IntArray, people: Int): Boolean {
        var cnt = 0

        for (stone in stones) {
            if (stone < people) cnt++
            else cnt = 0

            if (cnt == k) return false
        }
        return true
    }
}