package com.ssafy.algorithm

import java.util.LinkedList
import java.util.Queue

fun main() {
    val x = 2
    val y = 5
    val n = 4
    Solution().solution(x, y, n)
}

class Solution {
    var answer = Integer.MAX_VALUE
    val checked = BooleanArray(1000001)
    fun solution(x: Int, y: Int, n: Int): Int {
        if (x == y) answer = 0 // X <= y 라고 했으니 같을 떄 바로 0
        else answer = bfs(x, y, n)

        return answer
    }

    fun bfs(x: Int, y: Int, n: Int) : Int {
        val queue : Queue<Int> = LinkedList()
        queue.add(x) //초기값 세팅
        var count = 0

        while (queue.isNotEmpty()){
            for (i in queue.indices){
                val number = queue.poll()!! //현재 계산된 숫자
                if (number == y) return count //같으면 현재 count 반환

                if (number + n <= y && !checked[number+n]) { //y보다 작거나 같으면 중복 체크 및 queue에 계산된 값 추가
                    checked[number+n] = true
                    queue.add(number+n)
                }
                if (number * 2 <= y && !checked[number*2]) {
                    checked[number*2] = true
                    queue.add(number*2)
                }
                if (number * 3 <= y && !checked[number*3]) {
                    checked[number*3] = true
                    queue.add(number*3)
                }
            }
            count++
        }
        return -1
    }
}