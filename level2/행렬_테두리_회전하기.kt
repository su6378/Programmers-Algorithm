import kotlin.math.min

fun main() {
    val rows = 100
    val columns = 97
    val queries = arrayOf(intArrayOf(1,1,100,97))
    Solution().solution(rows, columns, queries)
}

class Solution {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        var answer = IntArray(queries.size)
        var matrix = Array(rows) { r -> IntArray(columns) { i -> (r * columns) + i + 1 } } //행렬 초기값 세팅

        queries.forEachIndexed { i, query ->
            var temp = Array(rows) { IntArray(columns) }
            var startR = query[0] - 1
            var startC = query[1] - 1
            var lastR = query[2] - 1
            var lastC = query[3] - 1
            var currentR = startR
            var currentC = startC + 1
            var direction = 1 //회전 방향
            var minNum = Integer.MAX_VALUE

            for (j in temp.indices){ //회전하는 영역을 제외하고 나머지는 원본 배열 복사
                for (k in temp[j].indices){
                    if (j in startR .. lastR && k in startC .. lastC) {
                        temp[j][k] = 0
                    }else temp[j][k] = matrix[j][k]
                }
            }

            while (true) {
                when (direction) {
                    1 -> { //오른쪽 방향
                        temp[currentR][currentC] = matrix[currentR][currentC-1]
                        minNum = min(minNum,temp[currentR][currentC])
                        if (currentC + 1 > lastC) { //마지막 위치에 도달하면
                            direction = 2
                            currentR++
                        } else currentC++
                    }
                    2 -> { //아래쪽 방향
                        temp[currentR][currentC] = matrix[currentR-1][currentC]
                        minNum = min(minNum,temp[currentR][currentC])
                        if (currentR + 1 > lastR) { //마지막 위치에 도달하면
                            direction = 3
                            currentC--
                        } else currentR++
                    }
                    3 -> { //왼쪽 방향
                        temp[currentR][currentC] = matrix[currentR][currentC+1]
                        minNum = min(minNum,temp[currentR][currentC])
                        if (currentC - 1 < startC) { //마지막 위치에 도달하면
                            direction = 4
                            currentR--
                        } else currentC--
                    }
                    4 -> { //위쪽 방향
                        temp[currentR][currentC] = matrix[currentR+1][currentC]
                        minNum = min(minNum,temp[currentR][currentC])
                        if (currentR - 1 < startR) { //마지막 위치에 도달하면
                            break
                        } else currentR--
                    }
                }
            }

            for (j in temp.indices){ //회전하지 않은 부분은 원본 배열 값으로 할당
                for (k in temp[j].indices){
                    if (temp[j][k] == 0) temp[j][k] = matrix[j][k]
                }
            }

            matrix = temp
            answer[i] = minNum
        }
        return answer
    }
}
