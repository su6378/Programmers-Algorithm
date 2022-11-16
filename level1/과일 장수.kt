package com.ssafy.algorithm

import kotlin.collections.ArrayList

fun main() {
    val k = 4
    val m = 3
    val score = intArrayOf(4,1,2,2,4,4,4,4,1,2,4,2)
    println(Solution().solution(k, m, score))
}

class Solution {
    fun solution(k: Int, m: Int, score: IntArray): Int {
        var answer = 0
        val scoreList = score.toMutableList() as ArrayList
        scoreList.sortDescending() //내림차순 정렬

        var count = 0
        val box = ArrayList<Int>()
        for (i in scoreList.indices){
            box.add(scoreList[i])
            count++
            if (count == m){ //최대 사과 개수되면 초기화
                answer += box.minOrNull()!! * m
                box.clear()
                count = 0
            }
        }
        return answer
    }
}