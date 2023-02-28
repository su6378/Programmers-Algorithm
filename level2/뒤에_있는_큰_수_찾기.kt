package com.example.algorithm

import java.util.*


fun main() {
    val number = intArrayOf(9, 1, 5, 3, 6, 2)
    Solution().solution(number)

}

class Solution {
    fun solution(numbers: IntArray): IntArray {
        var answer = IntArray(numbers.size)
        val stack = Stack<Int>()

        for (i in numbers.lastIndex downTo 0) {
            while (true) {
                if (stack.isEmpty()) { //스택이 비어있는 경우 : ex) 마지막 인덱스
                    answer[i] = -1
                    stack.push(numbers[i])
                    break
                } else {
                    if (numbers[i] >= stack.peek()) stack.pop() //현재 숫자보다 스택에 저장된 수가 작거나 같으면 pop
                    else { //더 큰 숫자가 나오면 스택에 현재 숫자 추가
                        answer[i] = stack.peek()
                        stack.push(numbers[i])
                        break
                    }
                }
            }
        }
        return answer
    }
}