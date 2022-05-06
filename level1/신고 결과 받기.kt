package com.example.test

class Solution {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        var answer = IntArray(id_list.size)

        val report_set = report.distinct()
        val report_arr = Array(id_list.size) { IntArray(id_list.size) }
        val report_userList = ArrayList<Int>()

        for (i in report_set.indices) {
            val report_user = report_set[i].split(" ")[0]
            val reported_user = report_set[i].split(" ")[1]
            report_arr[id_list.indexOf(report_user)][id_list.indexOf(reported_user)]++
        }

        var count = 0

        for (i in report_arr.indices) {
            for (j in report_arr[i].indices) {
                if (report_arr[j][i] == 1) {
                    count++
                    report_userList.add(j)
                }
            }
            if (count >= k) {
                for (a in report_userList.indices) {
                    answer[report_userList[a]]++
                }
            }
            count = 0
            report_userList.clear()
        }

        return answer
    }
}