fun main(){
    Solution().solution(name = arrayOf("may", "kein", "kain", "radi"), yearning = intArrayOf(5, 10, 1, 3), photo = arrayOf(
            arrayOf("may", "kein", "kain", "radi"),
            arrayOf("may", "kein", "brin", "deny"),
            arrayOf("kon", "kain", "may", "coni")
    ))
}

class Solution {
    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        var answer = IntArray(photo.size)
        val map = HashMap<String,Int>() // 이름, 그리움 점수 map

        for (i in name.indices){ // key,value 세팅
            map[name[i]] = yearning[i]
        }

        for (i in photo.indices){
            var score = 0
            for (j in photo[i].indices){ // 해당 이름이 있으면 점수 +
                if (map.containsKey(photo[i][j])) score += map.getValue(photo[i][j])
            }
            answer[i] = score
        }

        return answer
    }
}