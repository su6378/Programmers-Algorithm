package com.ssafy.algorithm


fun main(){
    val number = intArrayOf(-2,3,0,2,-5)
    println(Solution().solution(number))
}


class Solution {
    var answer = 0
    lateinit var visited: BooleanArray
    lateinit var number: IntArray

    fun solution(number: IntArray): Int {
        answer = 0
        visited = BooleanArray(number.size)
        permulation(number, 0, 0, 0)
        return answer
    }

    fun permulation(number: IntArray, start: Int, size: Int, sum: Int) { //숫자 조합
        if (size == 3) { //3개 뽑고 합이 0이면 answer++
            if (sum == 0) {
                answer++
            }
            return
        }
        for (i in start until number.size) { //start 변수를 선언해서 그 다음 반복문 실행 시 앞에 인덱스를 점프하고 실행
            if (!visited[i]) {                          //visited를 통해 중복으로 선택 불가능
                permulation(number, i + 1, size + 1, sum + number[i])
                visited[i] = false
            }
        }
    }
}