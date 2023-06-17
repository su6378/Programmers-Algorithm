fun main() {
    SolutionKotlin().solution(genres = arrayOf("hi", "hi", "hi", "hi","hi"), plays = intArrayOf(2,3,2,2,3))
}

class SolutionKotlin {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {

        var map = HashMap<String, ArrayList<Pair<Int, Int>>>() // 노래의 정보를 담는 map

        for (i in genres.indices) {
            if (!map.containsKey(genres[i])) map[genres[i]] = arrayListOf<Pair<Int, Int>>().apply { add(Pair(i, plays[i])) }
            else {
                map.getValue(genres[i]).add(Pair(i, plays[i]))
            }
        }

        // 장르가 하나인 경우에는 재생된 횟수를 기준으로 정렬하지 않아도 된다.
        if (map.size > 1) map = map.toList().sortedByDescending { it.second.sumOf { pair -> pair.second } }.toMap() as HashMap<String, ArrayList<Pair<Int, Int>>>

        var answer = ArrayList<Int>()

        map.values.forEach { playList ->
            if (playList.size >= 2) { // 해당 장르의 노래가 2개 이상인 경우에만 많이 재생된 노래 2개 뽑기
                playList.sortWith(compareByDescending<Pair<Int, Int>> { it.second }.thenBy { it.first })
                answer.add(playList[0].first)
                answer.add(playList[1].first)
            }else answer.add(playList[0].first)
        }

        return answer.toIntArray()
    }
}