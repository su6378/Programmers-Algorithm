import java.util.*

fun main() {
    SolutionKotlin().solution(gems = arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"))
}

class SolutionKotlin {
    fun solution(gems: Array<String>): IntArray {
        var answer = IntArray(2)
        val gemMap = HashMap<String,Int>()

        for(gem in gems) {
            gemMap[gem] = gemMap.getOrDefault(gem, 0) + 1
        }

        var start = 0
        val size = gemMap.size
        var count = 100000
        val map = HashMap<String,Int> ()

        // two-pointer
        for(end in gems.indices) {
            map[gems[end]] = map.getOrDefault(gems[end], 0) + 1

            while(map[gems[start]]!! > 1) {
                map[gems[start]] = map[gems[start]]!! - 1
                start++
            }

            if(map.size == size && count > end - start) {
                count = end - start
                answer[0] = start + 1
                answer[1] = end + 1
            }
        }
        return answer
    }
}