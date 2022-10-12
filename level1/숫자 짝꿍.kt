package com.example.test

import java.io.*
import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val X = "1"
    val Y = "1"
    println(Solution().solution(X, Y))


}

class Solution {
    fun solution(X: String, Y: String): String {
        val pair = IntArray(10)
        val pairList = ArrayList<Int>() //짝궁 숫자 리스트

        for (num in X){
            pair[num.digitToInt()]++
        }

        for (num in Y){
            if (pair[num.digitToInt()] > 0){ //짝을 만났을 때
                pairList.add(num.digitToInt())
            }
            pair[num.digitToInt()]--
        }

        pairList.sortDescending()

        val sb = StringBuilder()

        for (num in pairList){
            sb.append(num)
        }

        if (pairList.isEmpty()) return "-1"
        else if (pairList.sum() == 0) return "0"
        return sb.toString().toInt().toString()
    }
}