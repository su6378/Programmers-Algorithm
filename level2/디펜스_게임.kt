import java.util.PriorityQueue

fun main() {
    Solution().solution(n = 7, k = 3, enemy = intArrayOf(4, 2, 4, 5, 3, 3, 1))
}

class Solution {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        var round = 0
        if (k == enemy.size){ // 무적권이 라운드와 동일하면 패스
            round = k
        }else{
            var soldier = n
            val pq = PriorityQueue<Int>(reverseOrder()) // 내림차순 우선순위 큐
            var invisibleTicket = k
            for (i in enemy.indices){
                if (soldier < enemy[i]) {
                    if (invisibleTicket == 0) break
                    else { // 무적권이 남아있다면
                        pq.add(enemy[i])
                        soldier = soldier + pq.poll() - enemy[i] // 무적권을 사용하여 우선순위 큐에 가장 높은 수를 더해주고 적을 뺴준다.
                        invisibleTicket--
                        round++
                    }
                }
                else { //다음 라운드로
                    soldier -= enemy[i]
                    pq.add(enemy[i])
                    round++
                }
            }
        }
        return round
    }
}