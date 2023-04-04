import java.awt.Point
import kotlin.math.*

fun main() {
    Solution().solution(k = 5, ranges = arrayOf(intArrayOf(0, 0), intArrayOf(0, -1), intArrayOf(2, -3), intArrayOf(3, -3)))
}

class Solution {
    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
        val answer = DoubleArray(ranges.size)

        var kValue = k
        var index = 1
        val pointList = ArrayList<Point>()
        pointList.add(Point(0, k))

        while (true) { // 콜라츠 추측 숫자 만들기
            if (kValue == 1) break
            else if (kValue % 2 == 0) {
                kValue /= 2
                pointList.add(Point(index, kValue))
            } else if (kValue % 2 != 0) {
                kValue = kValue * 3 + 1
                pointList.add(Point(index, kValue))
            }
            index++
        }

        val areaMap = HashMap<IntArray,Double>()
        ranges.forEachIndexed { i, range ->
            val start = 0 + range[0]
            val end = pointList.size - 1 + range[1]
            var area = 0.0

            if (start > end) answer[i] = -1.0 // 유효 구간을 벗어나면
            else if (start == end) answer[i] = 0.0 // 영역 범위가 없을 때
            else {
                for (j in start until end){
                    if (areaMap.containsKey(intArrayOf(j,j+1))){
                        area += areaMap[intArrayOf(j,j+1)]!!
                    }else{
                        val triangleWidth : Double = abs(pointList[j].y - pointList[j+1].y) * abs(pointList[j].x - pointList[j+1].x) / 2.0 // 삼각형 영역
                        val squareWidth = min(pointList[j].y,pointList[j+1].y).toDouble() // 삼각형 밑에 사각형 영역
                        val width = triangleWidth + squareWidth
                        area += width
                        areaMap[intArrayOf(j,j+1)] = width
                    }
                }
                answer[i] = area
            }
        }
        return answer
    }
}