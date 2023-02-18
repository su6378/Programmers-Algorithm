package com.example.algorithm

fun main() {
    val number = 10
    val limit = 3
    val power = 2
    Solution().solution(number, limit, power)

}

class Solution {
    fun solution(number: Int, limit: Int, power: Int): Int {
        val countArr = IntArray(number)

        for (i in 1..number){
            countArr[i-1] = getAliquotCount(i)
            if (countArr[i-1] > limit) countArr[i-1] = power //약수의 개수가 limit보다 크면 power로 개신
        }
        return countArr.sum()
    }

    fun getAliquotCount(num: Int) : Int{ //약수 개수 구하기
        var count = 0
        var i = 1
        while (i * i <= num) {
            if (i * i == num) count++ else if (num % i === 0) count += 2
            i++
        }
        return count
    }
}