package com.ssafy.algorithm

fun main() {
    val food = intArrayOf(1,7,1,2)
    println(Solution().solution(food))
}

class Solution {
    fun solution(food: IntArray): String {
        val playerA = StringBuilder()

        food.forEachIndexed { index, count ->
            if (index > 0){
                if (count / 2 != 0 ) { //음식을 같이 먹을 수 있으면
                    repeat(count/2){ //음식의 개수 / 2한 값을 결과에 추가가
                       playerA.append(index)
                    }
                }
            }
        }

        val playerB = playerA.reversed()

        return "${playerA}0${playerB}"
    }
}

