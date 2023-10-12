import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

fun main() {
    SolutionKotlin().solution(n = 6, edge = arrayOf(intArrayOf(3, 6), intArrayOf(4, 3), intArrayOf(3, 2), intArrayOf(1, 3),
            intArrayOf(1, 2), intArrayOf(2, 4), intArrayOf(5, 2)))
}

const val INF = 20000

class SolutionKotlin {
    fun solution(n: Int, edge: Array<IntArray>): Int {
        var answer = 0
        var maxDepth = 0
        val graph = ArrayList<ArrayList<Int>>()
        val dist = IntArray(n + 1) { INF }
        val visited = BooleanArray(n + 1)

        dist[1] = 0
        repeat(n + 1) { graph.add(ArrayList()) }

        for (i in edge.indices) {
            graph[edge[i][0]].add(edge[i][1])
            graph[edge[i][1]].add(edge[i][0])
        }

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        pq.add(Pair(1, 0))

        while (pq.isNotEmpty()) {
            val now = pq.poll()

            if (visited[now.first]) continue
            visited[now.first] = true

            for (next in graph[now.first]) {
                if (dist[next] > now.second + 1) {
                    dist[next] = now.second + 1
                    maxDepth = max(maxDepth, dist[next])
                    pq.add(Pair(next, dist[next]))
                }
            }
        }

        for (i in 1 until dist.size){
            if (dist[i] == maxDepth) answer++
        }

        return answer
    }
}
