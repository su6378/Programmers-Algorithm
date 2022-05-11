package com.example.test

class Solution {
    fun solution(numbers: IntArray): String {
        val numbers_sort = arrayListOf<String>()
        for (i in numbers.indices) {
            numbers_sort.add(numbers[i].toString())
        }

        numbers_sort.sortWith(Comparator { a, b ->
            when {
                a.length == b.length -> b.compareTo(a)
                else -> (b + a).compareTo(a + b)
            }
        })

        if (numbers_sort[0] == "0") {
            return "0"
        } else {
            val sb = StringBuilder()
            for (i in numbers_sort.indices) {
                sb.append(numbers_sort[i])
            }
            return sb.toString()
        }
    }
}