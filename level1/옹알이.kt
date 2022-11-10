package com.ssafy.algorithm

import java.util.*

fun main() {
    val babbling = arrayOf("ayaye", "uuu", "yeye", "yemawoo", "ayaayaa")
    println(Solution().solution(babbling))
}

class Solution {
    val baby = arrayOf("aya", "ye", "woo", "ma")
    fun solution(babbling: Array<String>): Int {
        var answer: Int = 0

        for (word in babbling){
            val sb = StringBuilder()
            var isEnabled = true //발음할 수 있는 지 flag
            val stack = Stack<String>() //최근에 발음한 단어

            for (c in word){
                sb.append(c)
                when(sb.length){
                    2 -> { // "ye", "ma" check
                        val str = sb.toString()
                        if (str == "ye" || str == "ma"){
                            if (stack.isEmpty()) {
                                stack.push(str)
                                sb.setLength(0)
                            }
                            else {
                                if(stack.pop() == str){
                                    isEnabled = false
                                    break
                                }else {
                                    stack.push(str)
                                    sb.setLength(0)
                                }
                            }
                        }
                    }
                    3 -> { // "aya", "woo"
                        val str = sb.toString()
                        if (str == "aya" || str == "woo"){
                            if (stack.isEmpty()) {
                                stack.push(str)
                                sb.setLength(0)
                            }
                            else {
                                if(stack.pop() == str){
                                    isEnabled = false
                                    break
                                }else {
                                    stack.push(str)
                                    sb.setLength(0)
                                }
                            }
                        }else{
                            isEnabled = false
                            break
                        }
                    }
                }
            }

            if (sb.isNotEmpty()) isEnabled = false
            if(isEnabled) answer++
        }
        return answer
    }
}
