import java.util.*
import kotlin.math.*

fun main() {
    val n = 9
    val wires = arrayOf(intArrayOf(1, 3), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(4, 5), intArrayOf(4, 6),
            intArrayOf(4, 7), intArrayOf(7, 8), intArrayOf(7, 9))
    Solution().solution(n, wires)
}

class Solution {
    lateinit var tree: ArrayList<ArrayList<Int>>
    lateinit var visited: BooleanArray
    fun solution(n: Int, wires: Array<IntArray>): Int {
        var answer = Integer.MAX_VALUE
        tree = ArrayList()

        for (i in 0..n) {
            tree.add(ArrayList())
        }

        wires.forEach { wire -> //인접리스트에 관계 삽입
            tree[wire[0]].add(wire[1])
            tree[wire[1]].add(wire[0])
        }

        tree.forEachIndexed { i, node ->
            run {
                node.forEach { linkedNode ->
                    visited = BooleanArray(n + 1)
                    answer = min(answer, abs(bfs(i, linkedNode) - bfs(linkedNode, i)))
                    if (answer == 0) return@run //0이면 탐색할 필요없으므로 break
                }
            }
        }
        return answer
    }

    fun bfs(root: Int, linkedNode: Int): Int {
        val queue: Queue<Int> = LinkedList()
        queue.add(root)
        var count = 1

        //간선 연결 제거
        visited[root] = true
        visited[linkedNode] = true

        while (queue.isNotEmpty()) {
            val parentNode = queue.poll()
            tree[parentNode].forEach { node ->
                if (!visited[node]) {
                    visited[node] = true
                    queue.add(node)
                    count++
                }
            }
        }
        return count
    }
}