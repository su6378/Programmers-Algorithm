fun main() {
    Solution().solution(sequence = intArrayOf(2, 4, 6, 2, 4, 5,5,7), k = 12)
}

class Solution {
    lateinit var sumArr: IntArray // 누적 합 배열

    fun solution(sequence: IntArray, k: Int): IntArray {
        var answer: IntArray = intArrayOf(-1, -1)
        sumArr = IntArray(sequence.size)
        var left = 0 // 비교 시작 인덱스
        var minLength = Integer.MAX_VALUE

        out@ for (i in sequence.indices) {
            // 누적 합 배열 갱신
            if (i == 0) sumArr[i] = sequence[i]
            else sumArr[i] += sumArr[i - 1] + sequence[i]

            if (sequence[i] == k) { // sequence 요소 값이 k이면 break
                answer = intArrayOf(i, i)
                break@out
            } else {
                while (true) {
                    if (sumArr[i] == k && minLength == Integer.MAX_VALUE) { // sumArr[i] == k이고 아직 발견되지 않은 수열의 합일 경우 갱신
                        answer = intArrayOf(0, i)
                        minLength = i + 1
                    } else {
                        val sum = sumArr[i] - sumArr[left]
                        if (sum < k) { // sum이 작으면 바로 다음 반복문 실행
                            break
                        } else if (sum == k && i - left < minLength) { // sum == k이고 수열의 길이가 최소보다 작을 경우 갱신
                            answer = intArrayOf(left + 1, i)
                            minLength = i - left
                            break
                        } else { // 시작 인덱스 세팅
                            left++
                        }
                    }
                }
            }
        }
        return answer
    }
}