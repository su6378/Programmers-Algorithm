package com.ssafy.algorithm

fun main() {
    val orders = arrayOf("XYZ", "XWY", "WXA")
    val course = intArrayOf(2,3,4)
    println(Solution().solution(orders, course))
}

class Solution {
    lateinit var map : HashMap<String,Int> //메뉴와 주문 횟수 map
    lateinit var checked : BooleanArray

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        var answer = ArrayList<String>()

        for (size in course){
            map =  HashMap()
            var isBreak = true
            for (order in orders) {
                if (order.length >= size) { //코스메뉴 개수보다 크거나 같을 경우 탐색 시작
                    checked = BooleanArray(order.length)
                    combination(0, 0, size, order, CharArray(size))
                    isBreak = false
                }
            }
            if (isBreak) break //코스 메뉴 갯수보다 주문 문자열의 길이가 다 작을 경우 break

            val list = map.toList().sortedWith(compareByDescending {it.second})  //value값으로 내림차순 정렬
            val maxCount = list.first().second //최대로 나온 조합 횟수

            if(maxCount > 1){
                for (i in list.indices){
                    if (list[i].second < maxCount) break
                    answer.add(list[i].first)
                }
            }
        }

        println(answer.sorted())
        return answer.sorted().toTypedArray()
    }

    fun combination(start : Int, size : Int, max : Int, order : String, comb : CharArray){
        if (size == max){
            val courseMenu = comb.sorted().joinToString("")
            map.put(courseMenu,map.getOrDefault(courseMenu,0)+1)
            return
        }

        for (i in start until order.length){
            if (!checked[i]) {
                checked[i] = true
                comb[size] = order[i]
                combination(i+1,size+1,max,order,comb)
                checked[i] = false
            }
        }
    }
}


