import java.awt.Point
import kotlin.math.*

fun main() {
    Solution().solution(line = arrayOf(
            intArrayOf(2,-1,4),
            intArrayOf(-2,-1,4),
            intArrayOf(0,-1,1),
            intArrayOf(5,-8,-12),
            intArrayOf(5,8,12)
    ))
}

class Solution {
    // 별이 시작되는 x,y 좌표
    var startX = Integer.MAX_VALUE
    var startY = Integer.MAX_VALUE

    //별이 끝나는 x,y 좌표
    var endX = Integer.MIN_VALUE
    var endY = Integer.MIN_VALUE
    var pointSet = HashSet<Point>()

    fun solution(line: Array<IntArray>): Array<String> {

        dfs(0, 0, line, intArrayOf(0, 0))

        var answer : Array<String>

        if (pointSet.size == 1) answer = arrayOf("*")
        else{
            val starArr = Array(abs(endY - startY + 1)) { CharArray(endX - startX + 1) { '.' } } // 별표 배열 생성
            val sPoint = Point(startX,endY)

            for (point in pointSet) { // 시작점은 x좌표는 최소 y좌표는 최대인 곳에서 시작
                starArr[abs(point.y - sPoint.y)][abs(point.x - sPoint.x)] = '*'
            }

            answer = Array(abs(endY - startY + 1)) { "" }
            for (i in starArr.indices) {
                answer[i] = starArr[i].joinToString("")
            }
        }
        return answer
    }

    fun dfs(size: Int, start: Int, line: Array<IntArray>, point: IntArray) {
        if (size == 2) {
            // 서로 다른 두 직선이 교차하는지 체크
            val isCross = (line[point[0]][0] * line[point[1]][1]) - (line[point[1]][0] * line[point[0]][1]) // a1b2 - a2b1 == 0이면 평행 또는 일치
            if (isCross != 0) {
                getCrossPoint(line[point[0]], line[point[1]])
            }
            return
        }

        for (i in start until line.size) {
            point[size] = i
            dfs(size + 1, i + 1, line, point)
        }
    }

    fun getCrossPoint(a: IntArray, b: IntArray) { // 교점 구하기
        val pointX = ((a[1].toLong() * b[2].toLong()) - (a[2].toLong() * b[1].toLong())) / (((a[0].toLong() * b[1].toLong()) - (a[1].toLong() * b[0].toLong())).toDouble())
        val pointY = ((a[2] * b[0]) - (a[0] * b[2])) / (((a[0] * b[1]) - (a[1] * b[0])).toDouble())

        if (pointX - pointX.toInt() == 0.0 && pointY - pointY.toInt() == 0.0) { // 좌표가 정수이면 set에 추가
            startX = min(startX, pointX.toInt())
            endX = max(endX, pointX.toInt())
            startY = min(startY, pointY.toInt())
            endY = max(endY, pointY.toInt())
            pointSet.add(Point(pointX.toInt(), pointY.toInt()))
        }
    }
}