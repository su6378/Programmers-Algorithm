import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val k = 6
    val d = 17
    Solution().solution(k, d)
}

class Solution {
    fun solution(k: Int, d: Int): Long {
        var answer: Long = 0
        var lastIdx = (d / k) * k // 검사를 시작하는 인덱스가 0부터 k배수 이므로 마지막 인덱스를 k의 배수로 초기화 시키기 ex k = 6 d = 17이면 0,6,12이니깐 lastIdx는 12

        for (i in 0..d step k) {
            //뒤에서 부터 검사해서 해당 영역이 거리 안에 들어오면 다음 시작 인덱스를 해당 영역안에 들어왔을 떄의 j로 초기화 시키기
            for (j in lastIdx downTo 0 step k) {
                if (sqrt(i.toDouble().pow(2.0) + j.toDouble().pow(2.0)) <= d) {
                    lastIdx = j
                    break
                }
            }
            answer += (lastIdx / k) + 1
        }
        return answer
    }
}