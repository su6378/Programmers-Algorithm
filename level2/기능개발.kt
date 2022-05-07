package com.example.test

class Solution {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        var answer = arrayListOf<Int>()

        val pg_list = arrayListOf<Int>()
        val sp_list = arrayListOf<Int>()

        for (i in progresses.indices) {
            pg_list.add(progresses[i])
            sp_list.add(speeds[i])
        }

        var task = 0

        while (true) {
            if (pg_list.isEmpty()) break

            for (i in 0 until pg_list.size) {
                pg_list[i] += sp_list[i]

            }
            while (true) {
                if (pg_list.isNotEmpty()) {
                    if (pg_list[0] >= 100) {
                        pg_list.removeAt(0)
                        sp_list.removeAt(0)
                        task++
                    } else break
                } else break
            }
            if (task >= 1) {
                answer.add(task)
                task = 0
            }

        }

        return answer.toIntArray()
    }
}