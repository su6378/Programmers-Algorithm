import kotlin.collections.ArrayList
import kotlin.math.max

fun main() {
    SolutionKotlin().solution(
            info = intArrayOf(0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1),
            edges = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(1, 4), intArrayOf(0, 8),
                    intArrayOf(8, 7), intArrayOf(9, 10), intArrayOf(9, 11), intArrayOf(4, 3), intArrayOf(6, 5),
                    intArrayOf(4, 6), intArrayOf(8, 9))
    )
}

class SolutionKotlin {
    lateinit var infoArr: IntArray
    lateinit var graph: Array<IntArray>
    var answer = 0

    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        infoArr = info.clone()
        graph = Array(info.size) { IntArray(info.size) }

        for (i in edges.indices) {
            graph[edges[i][0]][edges[i][1]] = 1
        }

        if (info[0] == 0) dfs(0, 1, 0, arrayListOf(0))
        else dfs(0, 0, 1, arrayListOf(0))

        return answer
    }

    fun dfs(node: Int, sheep: Int, wolf: Int, nextList: ArrayList<Int>) {
        if (sheep <= wolf) return

        answer = max(answer, sheep)

        val list = ArrayList<Int>()

        list.addAll(nextList)
        list.remove(node)

        for (i in graph[node].indices) {
            if (graph[node][i] == 1) {
                list.add(i)
            }
        }

        for (next in list) {
            if (infoArr[next] == 0) dfs(next, sheep + 1, wolf, list)
            else dfs(next, sheep, wolf + 1, list)
        }
    }
}