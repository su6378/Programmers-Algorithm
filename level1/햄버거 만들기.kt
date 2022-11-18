package com.ssafy.algorithm

import java.util.*

fun main() {
    val ingredient = intArrayOf(2, 1, 1, 2, 3, 1, 2, 3, 1)
    println(Solution().solution(ingredient))
}

class Solution {
    fun solution(ingredient: IntArray): Int {
        var answer = 0
        val burger: Stack<Int> = Stack() //햄버거
        for (i in ingredient.indices) {
            if (burger.size >= 3) { //재료가 3개이상 쌓이면 햄버거를 포장할 수 있는 여부를 판단할 수 있음
                if (ingredient[i] == 1) { //1이 들어왔을 때 앞에 3개 요소를 비교후 햄버거 순서이면 pop 아니면 다시 push
                    val order3: Int = burger.pop()
                    val order2: Int = burger.pop()
                    val order1: Int = burger.pop()
                    if (order1 == 1 && order2 == 2 && order3 == 3) { //순서에 맞으면 +1
                        answer++
                    } else {
                        burger.push(order1)
                        burger.push(order2)
                        burger.push(order3)
                        burger.push(1)
                    }
                } else { //1이 아니라면 재료 추가
                    burger.push(ingredient[i])
                }
            } else { //재료가 3개이하라면 재료 추가
                burger.push(ingredient[i])
            }
        }
        return answer
    }
}