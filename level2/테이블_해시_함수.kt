
fun main() {
    Solution().solution(data = arrayOf(intArrayOf(2, 2, 6), intArrayOf(1, 5, 10), intArrayOf(4, 2, 9), intArrayOf(3, 8, 3)), col = 2, row_begin = 2, row_end = 3)
}

class Solution {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
        var answer = 0
        data.sortWith(compareBy<IntArray> { it[col-1] }.thenByDescending { it[0] }) // col 기준으로 정렬하고 값이 같으면 기본 키 순으로 내림차순 정렬

        for (i in row_begin - 1 until row_end){ //S_i 구하기
            var S = 0
            for (j in data[i].indices){
                S += data[i][j] % (i + 1)
            }
            answer = answer xor S
        }
        return answer
    }
}