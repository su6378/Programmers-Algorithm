package com.ssafy.algorithm

fun main() {
    val s = "aukks"
    val skip = "wbqd"
    val index = 5
    Solution().solution(s, skip, index)
}

class Solution {
    fun solution(s: String, skip: String, index: Int): String {
        var answer = StringBuilder()

        val skipList = ArrayList<Char>() //skip 알파벳 리스트
        for (alphabet in skip){
            skipList.add(alphabet)
        }

        for (alphabet in s){
            var nextIdx = 1 //이동할 인덱스 값
            var maxIdx = index //총 이동할 인덱스 값
            var currentChar = alphabet
            while (true){
                //다음 알파벳 (z이면 a부터 시작)
                val nextChar = if (currentChar == 'z') 'a' else (currentChar.code +1).toChar()

                if (skipList.contains(nextChar)){ //skip 문자열에 해당 문자가 포함되면
                    maxIdx++
                    nextIdx++
                }else nextIdx++

                currentChar = nextChar

                if (nextIdx > maxIdx) { //이동을 완료하면 break
                    answer.append(currentChar)
                    break
                }
            }
        }
        return answer.toString()
    }
}