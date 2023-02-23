package com.ssafy.algorithm

fun main() {
    val s = "aaabbaccccabba"
    Solution().solution(s)
}

class Solution {
    fun solution(s: String): Int {
        var first = s[0]
        var startCnt  = 1 //시작 문자 나오는 횟수
        var otherCnt = 0 //시작 문자와 다른 문자 횟수
        var isSplit = false //분해되었는지 여부
        var splitCnt = 0 //분해된 문자열 수

        for (i in 1 until s.length){
            if (isSplit) { //분해된 이후라면 초기화
                first = s[i]
                startCnt = 1
                otherCnt = 0
                isSplit = false
            }else{
                if (s[i] == first) startCnt++
                else otherCnt++

                if (startCnt == otherCnt){ //나온 횟수가 같다면
                    isSplit = true
                    splitCnt++
                }
            }
        }
        if (!isSplit) splitCnt++ //마지막에 분해되지 않은 문자열 카운트 세기
        return splitCnt
    }
}