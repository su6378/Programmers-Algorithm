import kotlin.math.min

fun main() {
    Solution().solution(picks = intArrayOf(0,1,1), minerals = arrayOf("diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"))
}

class Solution {
    lateinit var pickCount : IntArray // 곡괭이 갯수 배열
    var minCount = Integer.MAX_VALUE
    fun solution(picks: IntArray, minerals: Array<String>): Int {
        pickCount = intArrayOf(picks[0],picks[1],picks[2]) // 곡괭이 갯수 배열 초기화

        dfs(0,0,minerals)
        return minCount
    }

    fun dfs(index : Int, digCount : Int, minerals: Array<String>){
        if (5 * index >= minerals.size || !checkPickCount()){
            minCount = min(minCount,digCount)
            return
        }

        for (i in 0 until 3){
            if (pickCount[i] > 0){ // 남아있는 곡괭이 체크
                var temp = 0
                for (j in 5*index until 5*index+5){ // 5개씩 캐기
                    if (j < minerals.size){
                        when(i){
                            0 ->{ // 다이아몬드 곡괭이
                                when(minerals[j]){
                                    "diamond" -> temp += 1
                                    "iron" -> temp += 1
                                    "stone" -> temp += 1
                                }
                            }
                            1 ->{ // 철 곡괭이
                                when(minerals[j]){
                                    "diamond" -> temp += 5
                                    "iron" -> temp += 1
                                    "stone" -> temp += 1
                                }
                            }
                            2 ->{ // 돌 곡괭이
                                when(minerals[j]){
                                    "diamond" -> temp += 25
                                    "iron" -> temp += 5
                                    "stone" -> temp += 1
                                }
                            }
                        }
                    }
                }
                if (digCount + temp < minCount){ // 최소 피로도 넘어가면 return
                    pickCount[i]--
                    dfs(index+1,digCount+temp,minerals)
                    pickCount[i]++
                }
            }
        }
    }

    fun checkPickCount() : Boolean{ // 남아있는 곡괭이가 있는지 체크
        for (value in pickCount){
            if (value > 0 ) return true
        }
        return false
    }
}