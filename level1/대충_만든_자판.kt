import java.util.*
import kotlin.math.min

fun main() {
    val keymap = arrayOf("AGZ", "BSSS")
    val targets = arrayOf("ASA","BGZ")
    Solution().solution(keymap, targets)
}

class Solution {
    fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
        var answer = IntArray(targets.size)
        targets.forEachIndexed {i, target ->
            run{//break문 만들어주기
                target.forEach {
                    var kCount = Integer.MAX_VALUE //max값으로 초기화
                    keymap.forEach {key ->
                        key.forEachIndexed { index, c ->
                            if (c == it) kCount = min(kCount,index) //해당 문자를 찾을때 해당 인덱스를 kCount에 갱신
                        }
                    }
                    if (kCount == Integer.MAX_VALUE) { //해당 문자를 찾지 못하면 -1 세팅
                        answer[i] = -1
                        return@run
                    }
                    else answer[i] += kCount + 1 //누른 횟수이므로 +1
                }
            }
        }
        return answer
    }
}