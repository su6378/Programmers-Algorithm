fun main() {
    Solution().solution(park = arrayOf("SOO", "OOO", "OOO"), routes = arrayOf("E 2", "S 2", "W 1"))
}

class Solution {
    fun solution(park: Array<String>, routes: Array<String>): IntArray {

        var parkArr = Array(park.size) { Array(park[0].length) { 'O' } }
        var currentR = -1
        var currentC = -1

        for (i in park.indices) {
            for (j in park[i].indices) {
                if (park[i][j] != 'O') {
                    if (park[i][j] == 'S') {
                        currentR = i
                        currentC = j
                    } else parkArr[i][j] = park[i][j]
                }
            }
        }

        for (i in routes.indices) {
            val direction = routes[i].split(" ")[0] // 방향
            val moveCount = routes[i].split(" ")[1].toInt() // 이동 칸 수
            var isSkip = false // 명령어 스킵

            when (direction) { //방향에 따라 범위 체크 및 이동 중 장애물 체크하기
                "E" -> {
                    if (currentC + moveCount >= park[0].length) isSkip = true
                    else {
                        for (j in 1..moveCount) {
                            if (parkArr[currentR][currentC + j] == 'X') {
                                isSkip = true
                                break
                            }
                        }
                    }
                    if (!isSkip) currentC += moveCount
                }

                "W" -> {
                    if (currentC - moveCount < 0) isSkip = true
                    else {
                        for (j in 1..moveCount) {
                            if (parkArr[currentR][currentC - j] == 'X') {
                                isSkip = true
                                break
                            }
                        }
                    }
                    if (!isSkip) currentC -= moveCount
                }

                "S" -> {
                    if (currentR + moveCount >= park.size) isSkip = true
                    else {
                        for (j in 1..moveCount) {
                            if (parkArr[currentR + j][currentC] == 'X') {
                                isSkip = true
                                break
                            }
                        }
                    }
                    if (!isSkip) currentR += moveCount
                }

                "N" -> {
                    if (currentR - moveCount < 0) isSkip = true
                    else {
                        for (j in 1..moveCount) {
                            if (parkArr[currentR - j][currentC] == 'X') {
                                isSkip = true
                                break
                            }
                        }
                    }
                    if (!isSkip) currentR -= moveCount
                }
            }
        }
        return intArrayOf(currentR, currentC)
    }
}