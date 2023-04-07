import kotlin.math.min

fun main() {
    Solution().solution(name = "ABABAAAAABA")
}

class Solution {

    lateinit var visited: BooleanArray // DFS 방문 체크 배열
    var answer = Integer.MAX_VALUE

    fun solution(name: String): Int {

        visited = BooleanArray(name.length)
        var strArr = CharArray(name.length) { 'A' } // 시작할 문자열을 AAA로 초기화
        dfs(0, name.length, 0, name, strArr,0)

        return answer
    }

    fun dfs(index: Int, size: Int, count: Int, name: String, strArr: CharArray,beforeIdx : Int) {
        if (strArr.joinToString("") == name) { // 문자열이 같으면 return
            answer = count
            return
        }

        for (i in name.indices) {
            if (index == 0) { // 첫번째 시작은 무조건 0번째 인덱스
                if (i == 0) {
                    if (strArr[i] == name[i]) { // 'A'인 경우
                        strArr[i] = name[i]
                        visited[i] = true
                        dfs(1, size, count, name, strArr,0)
                    }
                    else {
                        strArr[i] = name[i]
                        visited[i] = true
                        dfs(1,size,count+upAndDown(name[i]),name,strArr,0)
                    }
                }
            } else {
                if (!visited[i]){ // 방문하지 않은 index
                    val lrCnt = leftAndRight(beforeIdx,i,size-1)
                    if (count + lrCnt < answer){
                        if (strArr[i] == name[i]) {
                            strArr[i] = name[i]
                            visited[i] = true
                            dfs(index+1, size, count+lrCnt, name, strArr,i)
                            visited[i] = false
                            strArr[i] = 'A'
                        }
                        else {
                            if (count + lrCnt + upAndDown(name[i]) < answer){
                                strArr[i] = name[i]
                                visited[i] = true
                                dfs(index+1,size,count+lrCnt+upAndDown(name[i]),name,strArr,i)
                                visited[i] = false
                                strArr[i] = 'A'
                            }
                        }
                    }
                }
            }
        }
    }

    fun upAndDown(target: Char): Int {
        val forwardCnt = target.toInt() - 'A'.toInt()
        val reverseCnt = 1 + 'Z'.toInt() - target.toInt()
        return min(forwardCnt, reverseCnt)
    }

    fun leftAndRight(current:Int, target: Int, last:Int) : Int{
        val leftCnt = if (target < current) current - target else current + 1 + (last - target)
        val rightCnt= if (current < target) target - current else last - current + 1 + target
        return min(leftCnt,rightCnt)
    }
}