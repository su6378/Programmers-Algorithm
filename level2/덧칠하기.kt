
fun main() {
    Solution().solution(n = 4, m = 1, section = intArrayOf(1,2,3,4))
}

class Solution {
    fun solution(n: Int, m: Int, section: IntArray): Int {
        var answer: Int = 0
        var area = IntArray(100001) //페인트를 칠할 구역

        for (paint in section){
            if (area[paint] == 0){ // 페인트를 다시 칠할 구역이면
                repeat(m) {count -> // 페인트의 길이(m)만큼 칠한다
                    if (paint+count <= 100000) area[paint+count] = 1 //영역 제한
                }
                answer++
            }
        }
        return answer
    }
}