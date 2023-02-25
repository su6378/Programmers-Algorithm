package com.ssafy.algorithm

fun main() {
    val cards1 = arrayOf("i", "water", "drink")
    val cards2 = arrayOf("want", "to")
    val goal = arrayOf("i", "want", "to", "drink", "water")
    Solution().solution(cards1, cards2, goal)
}

class Solution {
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        var answer = "Yes"
        var cardIdx1 = 0 //cards1의 사용할 카드 인덱스
        var cardIdx2 = 0 //cards2의 사용할 카드 인덱스
        for (word in goal){
           if (cards1[cardIdx1] == word || cards2[cardIdx2] == word){
               if (cards1[cardIdx1] == word) {
                   if (cardIdx1+1 == cards1.size) continue //마지막 인덱스일 경우에는 +1 하지않는다.
                   else cardIdx1++
               }
               else{
                   if (cardIdx2+1 == cards2.size) continue
                   else cardIdx2++
               }
           }else{
               answer = "No"
               break
           }
        }
        return answer
    }
}