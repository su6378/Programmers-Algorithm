
fun main() {
    Solution().solution(players = arrayOf("mumu", "soe", "poe", "kai", "mine"), callings = arrayOf("kai", "kai", "mine", "mine"))
}

class Solution {

    fun solution(players: Array<String>, callings: Array<String>): Array<String> {

        val playerMap = HashMap<String,Int>()
        val locationMap = HashMap<Int,String>()

        for (i in players.indices){ //초기 value 값 세팅
            playerMap[players[i]] = i
            locationMap[i] = players[i]
        }

        for (calling in callings){ // 호출할 때 마다 선수 위치에 대한 map 정보 갱신
            val callingIndex = playerMap[calling]!!
            val beforePlayer = locationMap[callingIndex-1]!!

            playerMap[beforePlayer] = callingIndex
            locationMap[callingIndex] = beforePlayer
            playerMap[calling] = callingIndex - 1
            locationMap[callingIndex - 1] = calling
        }

        val sortedList = playerMap.toList().sortedBy { it.second }.map { it.first }

        return sortedList.toTypedArray()
    }
}