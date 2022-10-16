package com.example.test

import java.util.*


fun main() {
    val order = intArrayOf(4,3,1,2,5)
    println(Solution().solution(order))
}

class Solution {
    fun solution(order: IntArray): Int {
        var answer = 0
        val container: Queue<Int> = LinkedList<Int>() // 컨테이너
        val subContainer: Stack<Int> = Stack<Int>() // 보조 컨테이너
        var start = 1 //보조 컨테이너 시작 인덱스
        var target = 0 //목표
        var isClear = true
        for (i in order.indices) {
            if (i + 1 == order[i]) { //order가 순서대로 되어있으면 아닐때 까지 answer++ 그리고 start++;
                answer++
                start++
            } else {
                target = i
                isClear = false
                break
            }
        }
        if (!isClear) { //순서적으로 다 들어가있지만 않으면 로직 실행
            for (i in order[target]..order.size) { //컨테이너 채우기
                container.offer(i)
            }
            for (i in start until order[target]) { //보조 컨테이너 채우기
                subContainer.push(i)
            }
            container.poll() //컨에티너 첫번째 인덱스는 order[target]부분과 일치하므로 poll
            answer++
            target++

            var isNext = true
            if (!container.contains(order[target])) {
                if (subContainer.peek() !== order[target]) { //만약 컨에티너에 주문 요소가 없고 보조컨테이너에 있으면 제일 앞부분에 없으면 로직종료
                    isNext = false
                }
            }

            if (isNext) {
                while (!container.isEmpty() || !subContainer.isEmpty()) {
                    if (!container.isEmpty()) {
                        if (container.peek() == order[target]) {
                            answer++
                            target++
                            container.poll()
                        } else {
                            if (!subContainer.isEmpty()) {
                                if (subContainer.peek() == order[target]) {
                                    answer++
                                    target++
                                    subContainer.pop()
                                } else {
                                    subContainer.push(container.poll())
                                }
                            } else {
                                subContainer.push(container.poll())
                            }
                        }
                    } else {
                        if (subContainer.peek() == order[target]) {
                            answer++
                            target++
                            subContainer.pop()
                        } else {
                            break
                        }
                    }
                }
            }
        }
        return answer
    }
}