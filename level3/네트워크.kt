import java.util.*
import kotlin.collections.ArrayList

fun main() {
    SolutionKotlin().solution(n = 3, computers = arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 1), intArrayOf(0, 1, 1)))
}

class SolutionKotlin {

    private lateinit var graph: ArrayList<ArrayList<Int>>
    private lateinit var checked: BooleanArray

    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0

        checked = BooleanArray(n)
        graph = ArrayList()

        repeat(n) {// 네트워크 갯수만큼 인접리스트 생성
            graph.add(ArrayList())
        }

        for (i in computers.indices) { // 양방향 간선 정보 삽입
            for (j in computers[i].indices){
                if (i != j && computers[i][j] == 1) graph[i].add(j)
            }
        }

        for (i in 0 until n){
            if (!checked[i]){ // 방문하지 않은 네트워크면 bfs탐색
                bfs(i)
                answer++
            }
        }

        return answer
    }

    fun bfs(start : Int){
        val queue: Queue<Int> = LinkedList()
        queue.add(start)
        checked[start] = true

        while (queue.isNotEmpty()){
            val startNetwork = queue.poll()
            for (next in graph[startNetwork]){
                if (!checked[next]) {
                    checked[next] = true
                    queue.add(next)
                }
            }
        }
    }
}