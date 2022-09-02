package com.ssafy.algorithm


import kotlin.math.*

fun main() {
    val sl = Solution()
    //val sizes = arrayOf(intArrayOf(60,50), intArrayOf(30,70), intArrayOf(60,30), intArrayOf(80,40))
    val sizes = arrayOf(intArrayOf(10,7), intArrayOf(12,3), intArrayOf(8,15), intArrayOf(14,7), intArrayOf(5,15))
//    val sizes = arrayOf(intArrayOf(14,4), intArrayOf(19,6), intArrayOf(6,16), intArrayOf(18,7),
//        intArrayOf(7,11))
    println(sl.solution(sizes))

}

class Solution {
    fun solution(sizes: Array<IntArray>): Int {
        var answer: Int = 0
        var max = 0
        var state = 0
        var maxIndex = -1
        for (i in sizes.indices){
            val width = sizes[i][0]
            val height = sizes[i][1]

            if (max < width) {
                max = width
                state = 1
                maxIndex = i
            }

            if (max < height){
                max = height
                state = 2
                maxIndex = i
            }
        }

        if (sizes[maxIndex][0] == sizes[maxIndex][1]){
            answer = max*max
        }else{
            var min = Int.MIN_VALUE
            if (state == 1){
                for (i in sizes.indices){
                    if (i == maxIndex){
                        continue
                    }else{
                       val h = min(sizes[i][0],sizes[i][1])
                       min = max(min,h)
                    }
                }
                min = max(min,sizes[maxIndex][1])
            }else if (state == 2){
                for (i in sizes.indices){
                    if (i == maxIndex){
                        continue
                    }else{
                        val w = min(sizes[i][0],sizes[i][1])
                        min = max(min,w)
                    }
                }
                min = max(min,sizes[maxIndex][0])
            }
            answer = max * min
        }
        return answer
    }
}

