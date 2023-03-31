fun main() {
    Solution().solution(weights = intArrayOf(100, 180, 360, 100, 270))
}

class Solution {
    fun solution(weights: IntArray): Long {
        var answer: Long = 0
        val wMap = HashMap<Double, Int>()

        weights.sort() // 같은 무게를 빨리 체크하기 위해 정렬 해주기

        for (i in weights.indices) { // 거리의 경우의 수로 나눠서 현재 몸무게에 해당 거리 비율에 map에 key가 존재하면 answer += 해당 value
            if (wMap.containsKey(1.0 * weights[i])) answer += wMap[1.0 * weights[i]]!!
            if(wMap.containsKey(1.0 / 2 * weights[i])) answer += wMap[1.0 / 2 * weights[i]]!!
            if(wMap.containsKey(2.0 / 3 * weights[i])) answer += wMap[2.0 / 3 * weights[i]]!!
            if(wMap.containsKey(3.0 / 4 * weights[i])) answer += wMap[3.0 / 4 * weights[i]]!!
            val wValue = wMap.getOrDefault(1.0 * weights[i], 0)
            wMap[1.0 * weights[i]] = wValue + 1
        }
        return answer
    }
}