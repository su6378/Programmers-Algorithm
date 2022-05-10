package com.example.test

class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = IntArray(2)
        val carpet_count = brown + yellow

        for (i in 1..carpet_count) {
            if (carpet_count % i == 0) {
                val h = i
                val w = carpet_count / i
                if (w < h) break
                else {
                    if ((w - 2) * (h - 2) == yellow) {
                        answer[0] = w
                        answer[1] = h
                    }
                }
            }
        }
        return answer
    }
}