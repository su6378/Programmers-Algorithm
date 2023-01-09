package com.example.algorithm

fun main() {
    val k = 2
    val tangerine = intArrayOf(10000000, 1, 1, 1, 2, 2, 2, 3)
    println(Solution().solution(k, tangerine))

}

class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {

        var sellCount = k
        var sizeCount = 0

        var map = HashMap<Int, Int>()

        for (value in tangerine) { //맵에 value 갱신해주기
            if (map.containsKey(value)) {
                map.put(value, map.getValue(value) + 1)
            } else { //종류 카운트 증가
                map.put(value, 1)
            }
        }

        var list = map.toList().sortedByDescending { it.second } //value 값을 기준으로 내림차순 정렬한 리스트

        for (value in list){ //value가 가장 큰 수 부터 귤 상자 크기만큼 빼서 목표 카운트에 도달하면 반복문 종료 그 동안 종류수는++
            sellCount -= value.second
            sizeCount++
            if (sellCount <= 0) break
        }

        return sizeCount
    }
}