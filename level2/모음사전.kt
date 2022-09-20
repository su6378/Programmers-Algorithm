package com.ssafy.algorithm

import kotlin.collections.ArrayList

fun main() {
    val sl = Solution()
    val word = "AAAE"
    println(sl.solution(word))
}


class Solution {
    lateinit var words : ArrayList<String>
    val sb = StringBuilder()
    val aeuiou = arrayOf("A", "E","I","O","U") //모음 배열
    fun solution(word: String): Int {
        words = ArrayList()
        var answer = 0
        for (i in 1..5){ //한글자부터 5글자까지 단어 만들기
            val word = Array(i){""}
            makeWord(i,0,word)
        }

        words.sort() //사전 순으로 정렬

        for (i in 0 until words.size){
            if (word == words[i]) answer = i + 1
        }
        return answer
    }
    fun makeWord(size : Int, length : Int, word : Array<String>){
        if (length == size){
            for (c in word){
                sb.append(c)
            }
            words.add(sb.toString())
            sb.setLength(0)
            return
        }

        for (i in aeuiou.indices){
            word[length] = aeuiou[i]
            makeWord(size,length+1,word)
        }
    }
}


