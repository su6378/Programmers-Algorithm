package com.example.test

class Solution {
    fun solution(citations: IntArray): Int {
        var answer = 0
        val arr = citations.sortedArrayDescending()

        for (i in arr.indices) {
            if (i >= arr[i]) {
                answer = i
                break
            } else answer = arr.lastIndex
        }
        return answer
    }
}