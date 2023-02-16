package com.ssafy.algorithm

fun main() {
    val s = "foobar"
    Solution().solution(s)
}

class Solution {
    fun solution(s: String): IntArray {
        var answer = IntArray(s.length)
        val hashMap = HashMap<Char,Int>() //해당 문자 위치정보를 담는 map

        for (i in s.indices){
            if (!hashMap.containsKey(s[i])){ //해당 문자가 앞에 없으면
                hashMap[s[i]] = i //해당 문자에 위치를 맵에 갱신
                answer[i] = -1
            }else{ //해당 문자가 앞에 있었다면
                answer[i] = i - hashMap.getValue(s[i])
                hashMap[s[i]] = i
            }
        }
        return answer
    }
}