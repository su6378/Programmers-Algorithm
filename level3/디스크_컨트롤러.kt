import java.util.*
import kotlin.collections.*

fun main() {
    SolutionKotlin().solution(
        jobs = arrayOf(intArrayOf(0, 16), intArrayOf(0, 14), intArrayOf(15, 1), intArrayOf(13, 13))
    )
}

class SolutionKotlin {
    fun solution(jobs: Array<IntArray>): Int {
        var answer = 0
        var time = 0
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })

        for (job in jobs) {
            pq.add(Pair(job[0], job[1]))
        }

        while (pq.isNotEmpty()) {
            val readyPq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

            while (pq.isNotEmpty() && time >= pq.peek().first) { // 대기중인 작업이 있으면 삽입
                readyPq.add(pq.poll())
            }

            if (readyPq.isEmpty()) { // 작업중일 때 대기중인 작업이 없으면 요청이 있는 작업이 나올때까지 skip
                time++
                continue
            }

            val work = readyPq.poll()
            time += work.second
            answer += time - work.first // 총 소요시간 계산

            while (readyPq.isNotEmpty()) {
                pq.add(readyPq.poll())
            }
        }

        return answer / jobs.size
    }
}
