import java.util.*
import kotlin.collections.ArrayList

fun main() {
    SolutionKotlin().solution(n = 4, costs = arrayOf(intArrayOf(0, 1, 1), intArrayOf(0, 2, 2), intArrayOf(1, 2, 5), intArrayOf(1, 3, 1), intArrayOf(2, 3, 8)))
}


class SolutionKotlin {
    fun solution(n: Int, costs: Array<IntArray>): Int {
        var answer = 0
        val visited = BooleanArray(n)
        val graph = ArrayList<ArrayList<Pair<Int, Int>>>()

        repeat(n) { graph.add(ArrayList()) }

        for (i in costs.indices) {
            val s = costs[i][0]
            val e = costs[i][1]
            val c = costs[i][2]

            graph[s].add(Pair(e, c))
            graph[e].add(Pair(s, c))
        }

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        pq.add(Pair(0, 0))

        while (pq.isNotEmpty()) {
            val now = pq.poll()

            if (visited[now.first]) continue

            visited[now.first] = true
            answer += now.second

            for (next in graph[now.first]) {
                if (!visited[next.first]) pq.add(next)
            }
        }

        return answer
    }
}
