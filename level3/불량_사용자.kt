import java.util.*

fun main() {
    val user_id = arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc")
    val banned_id = arrayOf("fr*d*", "*rodo", "******", "******")
    SolutionKotlin().solution(user_id, banned_id)
}

class SolutionKotlin {
    lateinit var set: HashSet<String>
    lateinit var map: HashMap<String, Int>
    lateinit var banList: Array<ArrayList<Int>>
    lateinit var visited: BooleanArray

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        set = HashSet()
        map = HashMap()
        banList = Array(banned_id.size) { ArrayList() }
        visited = BooleanArray(user_id.size)

        for (i in user_id.indices) {
            map[user_id[i]] = i
        }

        for (i in banned_id.indices) {
            for (j in user_id.indices) {
                if (banned_id[i].length != user_id[j].length) continue

                var isMatch = true

                for (k in 0 until banned_id[i].length) {
                    if (banned_id[i][k] == '*') continue  // *은 패스
                    if (banned_id[i][k] != user_id[j][k]) { // 문자가 일치하지 않는 경우
                        isMatch = false
                        break
                    }
                }

                if (isMatch) banList[i].add(map[user_id[j]]!!)
            }

            val banArr = IntArray(banned_id.size)

            comb(0, banned_id.size, banArr)
        }
        return set.size
    }

    fun comb(idx: Int, size: Int, banArr: IntArray) {
        if (idx == size) {
            val sb = StringBuilder()
            for (i in banArr.indices) {
                sb.append(banArr[i])
            }

            // 중복 되는 경우의 수를 체크하기 위해 정려
            val charArr = sb.toString().toCharArray()
            charArr.sort()

            val result = String(charArr)
            set.add(result)
            return
        }

        for (i in banList[idx].indices) {
            if (!visited[banList[idx][i]]) {
                visited[banList[idx][i]] = true
                banArr[idx] = banList[idx][i]
                comb(idx + 1, size, banArr)
                visited[banList[idx][i]] = false
            }
        }
    }
}