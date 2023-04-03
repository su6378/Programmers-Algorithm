import java.awt.Point
import java.util.*

fun main() {
    Solution().solution(maps = arrayOf("SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"))
}

class Solution {
    // 방향 벡터
    val dx = intArrayOf(0, 0, -1, 1)
    val dy = intArrayOf(-1, 1, 0, 0)
    lateinit var visited: Array<BooleanArray> // 방문 체크 배열

    fun solution(maps: Array<String>): Int {
        val start = Point() // 시작 좌표
        visited = Array(maps.size) { BooleanArray(maps[0].length) }

        for (i in maps.indices) {
            for (j in maps[i].indices) {
                if (maps[i][j] == 'S') {
                    start.x = j
                    start.y = i
                }
            }
        }
        return bfs(start,maps)
    }

    fun bfs(start: Point, maps: Array<String>): Int {
        val queue: Queue<Point> = LinkedList()
        queue.add(start)
        visited[start.y][start.x] = true
        var isPull = false // 레버를 당겼는지 체크하는 boolean
        var isReset = false // 방문 체크 배열 초기화 했는지 boolean
        var count = 0 // 이동한 횟수
        val lPoint = Point() //레버 포인트

        while (queue.isNotEmpty()) {

            count++

            if (isPull && isReset){ // 레버를 당겼을 경우 초기화 시키기
                queue.clear()
                visited = Array(maps.size) { BooleanArray(maps[0].length) }
                visited[lPoint.y][lPoint.x] = true
                queue.add(lPoint)
                isReset = false
            }

            val qSize = queue.size

            for (loop in 0 until qSize){
                val point = queue.poll()
                for (i in dx.indices) {
                    val pointX = point.x + dx[i]
                    val pointY = point.y + dy[i]

                    if (pointX in maps[0].indices && pointY in maps.indices && !visited[pointY][pointX] && maps[pointY][pointX] != 'X') { // 해당 조건을 만족하면
                        when (maps[pointY][pointX]) {
                            'O','S' -> { // 일반 통로
                                queue.add(Point(pointX, pointY))
                                visited[pointY][pointX] = true
                            }

                            'L' -> { // 레버
                                isPull = true
                                isReset = true
                                lPoint.x = pointX
                                lPoint.y = pointY
                                queue.add(Point(pointX,pointY))
                                visited[pointY][pointX] = true
                            }

                            'E' -> { // 탈출구
                                if (isPull) return count
                                else {
                                    queue.add(Point(pointX, pointY))
                                    visited[pointY][pointX] = true
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1
    }
}