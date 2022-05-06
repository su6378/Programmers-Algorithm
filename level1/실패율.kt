package com.example.test

class Solution {
   fun solution(N: Int, stages: IntArray): IntArray {
    val stage = IntArray(N + 2)
    var user = stages.size
    val failPair = ArrayList<Pair<Int, Double>>()
    for (i in stages.indices) {
        stage[stages[i]]++
    }

    for (i in 1..N) {
        if (user == 0) {
            failPair.add(Pair(i, 0.0))
        } else {
            var fail = stage[i].toDouble() / user.toDouble()
            failPair.add(Pair(i, fail))
            user -= stage[i]
        }
    }

    failPair.sortWith(compareByDescending { it.second })


    var answer = IntArray(N)

    for (i in failPair.indices) {
        answer[i] = failPair.get(i).first
    }
    return answer
}

}