import java.util.*
import kotlin.math.*

fun main() {
    val storey = 2554
    Solution().solution(storey)
}

class Solution {
    var answer = 0
    var visitted = BooleanArray(100000001)
    fun solution(storey: Int): Int {
        answer = btnPush(storey)
        return answer
    }

    fun btnPush(storey: Int): Int { //bfs 접근
        var count = 0
        var c: Int
        val queue: Queue<Int> = LinkedList()
        queue.add(storey)
        visitted[storey] = true //해당 층 방문

        while (queue.isNotEmpty()) {
            for (i in queue.indices) {
                c = 0 //지수는 매번 층마다 초기화시켜주기
                val floor = queue.poll()

                if (floor % 10.0.pow(c + 1).toInt() == 0) { // 해당 자리수로 나머지를 구했을 때 0이면 다음 자리수로 검색하기 위한 로직
                    while (true) {
                        if (floor % 10.0.pow(c + 1).toInt() == 0) c++
                        else break
                    }
                }

                if (!visitted[floor - 10.0.pow(c).toInt()]) { // -버튼
                    if (floor - 10.0.pow(c).toInt() == 0 || floor == 10.0.pow(c + 1).toInt()) return count + 1
                    else {
                        visitted[floor - 10.0.pow(c).toInt()] = true
                        queue.add(floor - 10.0.pow(c).toInt())
                    }
                }

                if (!visitted[floor + 10.0.pow(c).toInt()]) { //+버튼
                    if (floor + 10.0.pow(c).toInt() == 0 || floor == 10.0.pow(c + 1).toInt()) return count + 1
                    else {
                        visitted[floor + 10.0.pow(c).toInt()] = true
                        queue.add(floor + 10.0.pow(c).toInt())
                    }
                }
            }
            count++
        }
        return count
    }
}