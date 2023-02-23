package com.example.algorithm


fun main() {
    val topping = intArrayOf(1, 2, 1, 3, 1, 4, 1, 2)
    Solution().solution(topping)

}

class Solution {
    fun solution(topping: IntArray): Int {
        var answer = 0

        val chulsu = IntArray(10001)
        val brother = IntArray(10001)
        var cCnt = 1
        var bCnt = 0

        //처음 케이크 사이즈 세팅
        chulsu[topping[0]] = 1

        for (i in 1 until topping.size){
            if (brother[topping[i]] == 0) bCnt++
            brother[topping[i]]++
        }

        if (cCnt == bCnt) answer++ //처음 잘랐을 때 토핑 종류가 같으면 ++

        //케이크 자르기
        for (i in 1 until topping.size-1){
            if (chulsu[topping[i]] == 0) cCnt++
            chulsu[topping[i]]++
            if (brother[topping[i]] == 1) bCnt--
            brother[topping[i]]--

            if (cCnt == bCnt) answer++
        }

        println(answer)

        return answer
    }
}