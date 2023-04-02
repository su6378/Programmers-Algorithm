fun main() {
    Solution().solution(relation = arrayOf(
            arrayOf("100","ryan","music","2"),
            arrayOf("200","apeach","math","2"),
            arrayOf("300","tube","computer","3"),
            arrayOf("400","con","computer","4"),
            arrayOf("500", "muzi", "music", "3"),
            arrayOf("600", "apeach", "music", "2")
    ))
}

class Solution {
    val map = HashMap<ArrayList<Int>, Boolean>() //후보키를 담는 맵
    val candidateList = ArrayList<ArrayList<Int>>() //후보키를 담는 리스트
    val keyList = ArrayList<Int>() // 키 조합 리스트
    var answer = 0

    fun solution(relation: Array<Array<String>>): Int {

        for (i in 0 until relation[0].size) {
            searchCandidateKey(0, 0, i + 1, relation)
        }
        return answer
    }

    fun searchCandidateKey(index: Int, start: Int, size: Int, relation: Array<Array<String>>) {
        if (index == size) {
            if (checkDuplication(relation, keyList)) { // 후보키를 만족하면
                answer++
                val candidate = ArrayList<Int>()
                for (key in keyList) {
                    candidate.add(key)
                }
                candidateList.add(candidate)
                map[keyList] = true
            }
            return
        }

        for (i in start until relation[0].size) {
            keyList.add(i)
            if (!map.containsKey(keyList)) { // 후보키에 해당 되지 않을 때 실행
                var isContain = false
                for (j in candidateList.indices) { // 키 조합 리스트가 후보키를 포함할 때 체크 ex {1,2,3}일 떄 후보키가 {2,3}
                    var flag = true
                    for (k in candidateList[j].indices) {
                        if (!keyList.contains(candidateList[j][k])) {
                            flag = false
                            break
                        }
                    }
                    if (flag) {
                        isContain = true
                        break
                    }
                }
                if (isContain) keyList.removeLast()
                else {
                    searchCandidateKey(index + 1, i + 1, size, relation)
                    keyList.removeLast()
                }
            } else keyList.removeLast()
        }
    }

    fun checkDuplication(relation: Array<Array<String>>, keyList: ArrayList<Int>): Boolean {
        val dataList = ArrayList<ArrayList<String>>()

        for (i in relation.indices) { // 중복되는 데이터가 없는지 체크
            val dataInfo = ArrayList<String>()
            for (j in keyList.indices) {
                dataInfo.add(relation[i][keyList[j]])
            }
            if (dataList.contains(dataInfo)) return false
            else (dataList.add(dataInfo))
        }

        return true
    }
}