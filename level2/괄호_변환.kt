package com.example.algorithm

import java.util.*


fun main() {
    val p = ")()()()("
    Solution().solution(p)

}

class Solution {
    fun solution(p: String): String {
        var answer = ""
        if (!checkBracket(p)) answer = changeBracket(p)
        else answer = p
        return answer
    }

    fun checkBracket(p:String) : Boolean { //올바른 문자열인지 체크
        val stack = Stack<Char>()
        for (i in p.indices){
            if (p[i] == '(') {
                stack.push('(')
            }
            else{
                if (stack.isNotEmpty()){
                    if (stack.peek() == '(') {
                        stack.pop()
                    }
                    else stack.push(')')
                }else return false
            }
        }
        return true
    }

    fun changeBracket(p:String) : String{ //올바른 문자열로 바꾸기
        if(p.isEmpty()) return "" //빈 문자열 반환

        val sb = StringBuilder()
        val stack = Stack<Char>()
        var oCnt = 0 //여는 괄호 수
        var cCnt = 0 //닫는 괄호 수

        for (i in p.indices){
            if (p[i] == '(') {
                stack.push('(')
                oCnt++
            }
            else{
                if (stack.isNotEmpty()){
                    if (stack.peek() == '(') {
                        stack.pop()
                    }
                    else stack.push(')')
                }else stack.push(')')
                cCnt++
            }

            if (oCnt == cCnt){ //여는 괄호와 닫는 괄호가 같을 때 u,v로 분리하기
                val u = p.substring(0,i+1)
                val v = p.substring(i+1)
                if (stack.isEmpty()){ //u가 올바른 문자열일 때
                    sb.append(u)
                    sb.append(changeBracket(v)) //v부터 다시 변환
                    break
                }else{
                    val reverseU = StringBuilder()
                    for (j in 1 until u.length-1){ //u 괄호 뒤집기
                        when(u[j]){
                            '(' -> reverseU.append(')')
                            else -> reverseU.append('(')
                        }
                    }
                    sb.append('(').append(changeBracket(v)).append(')').append(reverseU)
                    break
                }
            }
        }
        return sb.toString()
    }
}