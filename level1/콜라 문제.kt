package com.ssafy.algorithm

fun main() {
    val a = 3
    val b = 1
    val n = 20
    println(Solution().solution(a, b, n))
}

class Solution {
    fun solution(a: Int, b: Int, n: Int): Int {
        var n = n
        var answer = 0
        while (true) {
            val cola = n / a * b //콜라로 교환
            n %= a //빈병
            answer += cola //콜라 갯수 만큼 더하기
            n += cola
            if (cola <= 0) { //교환할 수 있는 콜라가 0개면 무한루프 탈출
                break
            }
        }
        return answer
    }
}