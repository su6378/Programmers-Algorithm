package com.example.test


fun main() {
    val want = arrayOf("banana", "apple", "rice", "pork", "pot")
    val number = intArrayOf(3,2,2,2,1)
    val discount = arrayOf("chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana")
}

class Solution {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        var answer = 0
        var wantCount = 0
        for (i in number) { //몇일인지 총갯수 계산
            wantCount += i
        }
        for (i in 0..discount.size - wantCount) { //갯수 만큼 인덱스 시작
            val map = HashMap<String, Int>()
            for (j in want.indices) { //map에 want인덱스 넣어주기
                map[want[j]] = number[j]
            }
            for (j in i until i + wantCount) { //첫째날, 둘째날, ..... want갯수까지
                if (map.containsKey(discount[j])) { //같으면 value값 감소
                    map[discount[j]] = map[discount[j]]!! - 1
                    if (map[discount[j]] === 0) {
                        map.remove(discount[j])
                    }
                    if (map.isEmpty()) {
                        answer++
                        break
                    }
                }
            }
        }
        return answer
    }
}