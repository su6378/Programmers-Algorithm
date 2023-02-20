package com.ssafy.algorithm

fun main() {
    val t = "10203"
    val p = "15"
    Solution().solution(t, p)
}

class Solution {
    fun solution(t: String, p: String): Int {
        var answer: Int = 0

        val loop = t.length - p.length //부분문자열 시작 인덱스의 마지막 인덱스

        for (i in 0..loop) {
            val subtext = t.substring(i, i + p.length)
            if (subtext <= p) answer++
        }

        return answer
    }
}