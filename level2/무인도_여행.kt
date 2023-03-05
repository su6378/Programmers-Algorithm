import java.util.LinkedList
import java.util.Queue

fun main() {
    val maps = arrayOf("X591X", "X1X5X", "X231X", "1XXX1")
    Solution().solution(maps)
}

class Solution {

    data class Point(val x: Int, val y: Int)

    val dx = intArrayOf(0, 0, -1, 1)
    val dy = intArrayOf(-1, 1, 0, 0)
    lateinit var visited: Array<BooleanArray>

    fun solution(maps: Array<String>): IntArray {
        var answer = ArrayList<Int>()

        visited = Array(maps.size) { BooleanArray(maps[0].length) }

        for (i in maps.indices) {
            for (j in maps[i].indices) {
                if (!visited[i][j] && maps[i][j] != 'X') { //방문한 적이 없고 X가 아니면 탐색 시작
                    answer.add(bfs(Point(j, i), maps))
                }
            }
        }
        return if (answer.isEmpty()) intArrayOf(-1) else answer.sorted().toIntArray() //비어있다면 -1 리턴 아니면 정렬하고 리턴
    }

    fun bfs(start: Point, maps: Array<String>): Int {
        val queue: Queue<Point> = LinkedList()
        queue.add(start)
        visited[start.y][start.x] = true
        var day = maps[start.y][start.x].digitToInt() // 지낼 수 있는 일수

        while (queue.isNotEmpty()) {
            val point = queue.poll()

            for (i in dx.indices) {
                val x = point.x + dx[i]
                val y = point.y + dy[i]

                if (x in maps[0].indices && y in maps.indices && maps[y][x] != 'X' && !visited[y][x]) { //영역을 벗어나지 않고 무인도에 방문하였을 때
                    day += maps[y][x].digitToInt()
                    queue.add(Point(x, y))
                    visited[y][x] = true
                }
            }
        }
        return day
    }
}