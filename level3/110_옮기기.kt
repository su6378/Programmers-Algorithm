import java.lang.StringBuilder
import java.util.*

fun main() {
    SolutionKotlin().solution(s = arrayOf("1110", "100111100", "0111111010"))
}

class SolutionKotlin {
    fun solution(s: Array<String>): Array<String> {
        val answer = Array(s.size) { "" }
        val sb = StringBuilder()

        for (i in s.indices) {
            val str = s[i]
            val stack = Stack<Char>()
            var cnt = 0

            for (j in str.indices) {
                val c = str[j]

                if (stack.size > 1) { // 스택의 크기가 2이상일 경우 "110" 문자열 체크
                    val b = stack.pop()
                    val a = stack.pop()

                    if (a == '1' && b == '1' && c == '0') cnt++
                    else { // "110"이 아니라면 다시 스택에 넣기
                        stack.push(a)
                        stack.push(b)
                        stack.push(c)
                    }
                } else stack.push(c)
            }

            // 문자열에 "110"이 존재할 경우
            if (cnt > 0) {
                var insertIdx = stack.size
                var isLast = false

                // 마지막 0 위치 찾기
                while (stack.isNotEmpty()) {
                    if (!isLast) {
                        if (stack.peek() == '1') insertIdx--
                        else isLast = true
                    }

                    sb.insert(0, stack.pop())
                }

                while (cnt-- > 0) {
                    sb.insert(insertIdx, "110")
                    insertIdx += 3
                }

                answer[i] = sb.toString()
            } else answer[i] = s[i]

            sb.setLength(0)
        }

        return answer
    }
}