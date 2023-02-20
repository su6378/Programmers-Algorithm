package com.example.algorithm

import java.util.PriorityQueue

fun main() {
    val k = 4
    val score = intArrayOf(0, 300, 40, 300, 20, 70, 150, 50, 500, 1000)
    Solution().solution(k, score)

}

class Solution {
    fun solution(k: Int, score: IntArray): IntArray {
        var answer =  IntArray(score.size)
        val pq = PriorityQueue<Int>() //명예의 전당

        for (i in score.indices){
            pq.add(score[i])
            if (pq.size > k) pq.poll()
            answer[i] = pq.peek() //최하위 가수 발표
        }
        return answer
    }
}