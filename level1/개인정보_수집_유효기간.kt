package com.example.algorithm

fun main() {
    val today = "2020.01.01"
    val terms = arrayOf("Z 3", "D 5")
    val privacies = arrayOf("2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z")
    Solution().solution(today, terms, privacies)

}

class Solution {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        var answer = ArrayList<Int>()
        val t = today.split(".").joinToString("") //today를 비교하기 위해 정수형 숫자로 변환

        val termMap = HashMap<Char,Int>() //약관 종류의 정보를 담은 map
        for (term in terms){
            val info = term.split(" ")
            termMap[info[0][0]] = info[1].toInt()
        }

        for (i in privacies.indices){
            val privacy = privacies[i].split(".") //개인정보
            var pYear = privacy[0].toInt() //year
            var pMonth = privacy[1].toInt() //month
            var pDay = privacy[2].split(" ")[0].toInt() //day
            var termType = privacy[2].split(" ")[1] //약관 종류

            val termPeriod = termMap[termType[0]] //해당 약관의 유효기간
            pMonth += termPeriod!! //유효기간만큼 더해주기
            if (pMonth > 12){
                if (pMonth % 12 == 0){ // ex)24,36일 때 처리
                    pYear += (pMonth / 12) - 1
                    pMonth = 12
                }else{
                    pYear += pMonth / 12
                    pMonth %= 12
                }
            }

            if (pDay - 1 == 0){
                pDay = 28
                pMonth--
            }else pDay--

            val pDate = StringBuilder()
            if (pMonth > 9) pDate.append(pYear).append(pMonth)
            else pDate.append(pYear).append(0).append(pMonth)

            if (pDay > 9) pDate.append(pDay)
            else pDate.append(0).append(pDay)

            if (t.toInt() > pDate.toString().toInt()) answer.add(i+1)
        }
        return answer.toIntArray()
    }
}