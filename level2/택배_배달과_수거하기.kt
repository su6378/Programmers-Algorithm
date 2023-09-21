import java.util.*
import kotlin.math.max

fun main() {
    val cap = 4
    val n = 5
    val deliveries = intArrayOf(1, 0, 3, 1, 2)
    val pickups = intArrayOf(0, 3, 0, 4, 0)

    SolutionKotlin().solution(cap, n, deliveries, pickups)
}


class SolutionKotlin {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer = 0L
        val dStack = Stack<Int>()
        val pStack = Stack<Int>()

        repeat(n) {
            if (deliveries[it] > 0) dStack.add(it)
        }

        repeat(n) {
            if (pickups[it] > 0) pStack.add(it)
        }

        while (true) {
            if (!dStack.isEmpty() && !pStack.isEmpty()) { // 배달과 수거할 물품이 있는 경우
                answer += (max(dStack.peek(), pStack.peek()) + 1) * 2L
            }
            if (!dStack.isEmpty() && pStack.isEmpty()) { // 수거할 물품만 있는 경우
                answer += (dStack.peek() + 1) * 2L
            }
            if (dStack.isEmpty() && !pStack.isEmpty()) { // 배달할 물품만 있는 경우
                answer += (pStack.peek() + 1) * 2L
            }
            if (dStack.isEmpty() && pStack.isEmpty()) break

            var dCount = cap

            while (!dStack.isEmpty()) {
                if (dCount <= 0) break
                if (deliveries[dStack.peek()] <= dCount) dCount -= deliveries[dStack.pop()] else {
                    deliveries[dStack.peek()] -= dCount
                    break
                }
            }

            var pCount = 0

            while (!pStack.isEmpty()) {
                if (pCount == cap) break
                if (pickups[pStack.peek()] + pCount <= cap) pCount += pickups[pStack.pop()] else {
                    pickups[pStack.peek()] -= cap - pCount
                    break
                }
            }
        }

        return answer
    }
}