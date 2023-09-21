import java.util.*

fun main() {
    val tickets = arrayOf(
        arrayOf("ICN", "SFO"),
        arrayOf("ICN", "ATL"),
        arrayOf("SFO", "ATL"),
        arrayOf("ATL", "ICN"),
        arrayOf("ATL", "SFO")
    )
    println(SolutionKotlin().solution(tickets).contentToString())
}


const val MAX_SIZE = 10000

class SolutionKotlin {

    lateinit var map: HashMap<String, Int>
    lateinit var graph: ArrayList<ArrayList<Int>>
    lateinit var paths: ArrayList<String>
    lateinit var cities: Array<String>
    lateinit var visited: Array<IntArray> // 주어진 티켓이 중복일 경우를 고려한 체크 배열

    fun solution(tickets: Array<Array<String>>): Array<String> {
        val answer = Array(tickets.size + 1) { "" }
        var value = 0
        var start = 0
        var end = 0
        map = HashMap()
        graph = ArrayList()
        paths = ArrayList()
        cities = Array(MAX_SIZE) { "" }
        visited = Array(MAX_SIZE) { IntArray(MAX_SIZE) }

        // 인천공항에서 모든 경로가 시작되니 0으로 초기화
        map.put("ICN", 0)
        cities[0] = "ICN"

        repeat(MAX_SIZE) { graph.add(ArrayList()) }

        for (ticket in tickets) {  // 경로 갱신
            if (!map.containsKey(ticket[0])) {
                start = ++value
                map.put(ticket[0], start)
                cities[start] = ticket[0]
            } else start = map.get(ticket[0])!!

            if (!map.containsKey(ticket[1])) {
                end = ++value
                map.put(ticket[1], end)
                cities[end] = ticket[1]
            } else end = map.get(ticket[1])!!

            visited[start][end]++
            graph[start].add(end)
        }

        val arr = IntArray(tickets.size + 1)

        dfs(0, 1, tickets.size + 1, arr)

        paths.sort()

        val path = paths[0]
        val sb = StringBuilder()
        var idx = 0

        for (i in path.indices) {
            sb.append(path[i])

            if (i % 3 == 2) {
                answer[idx++] = sb.toString()
                sb.setLength(0)
            }
        }
        return answer
    }

    fun dfs(city: Int, idx: Int, size: Int, arr: IntArray) {
        if (idx == size) {
            val sb = StringBuilder()

            for (i in arr.indices) {
                sb.append(cities[arr[i]])
            }

            paths.add(sb.toString())
            return
        }

        for (next in graph[city]) {
            if (visited[city][next] > 0) {
                visited[city][next]--
                arr[idx] = next
                dfs(next, idx + 1, size, arr)
                visited[city][next]++
            }
        }
    }
}