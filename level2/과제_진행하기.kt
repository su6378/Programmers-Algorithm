import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

fun main() {
    Solution().solution(plans = arrayOf(
            arrayOf("korean", "11:40", "30"),
            arrayOf("english", "12:10", "20"),
            arrayOf("math", "12:30", "40"),
    ))
}

class Solution {
    fun solution(plans: Array<Array<String>>): Array<String> {
        val answer = ArrayList<String>()
        plans.sortBy { it[1] }
        val waitStack = Stack<Array<String>>()
        var progressing = arrayOf("")
        for (i in plans.indices) {
            if (progressing.contentEquals(arrayOf(""))) {
                progressing = plans[i]
            } else {
                // 다음 작업과 현재 진행중인 작업 시간 차이
                var leftTime = 0
                var lHour = plans[i][1].split(":")[0].toInt()
                var lMinute = plans[i][1].split(":")[1].toInt()

                if (lMinute - progressing[1].split(":")[1].toInt() < 0) {
                    var count = 0

                    while (true) {
                        if (60 * count > abs(lMinute - progressing[1].split(":")[1].toInt())) break
                        count++
                    }

                    lMinute = 60 * count + (lMinute - progressing[1].split(":")[1].toInt())
                    lHour -= (count + progressing[1].split(":")[0].toInt())
                } else {
                    lMinute -= progressing[1].split(":")[1].toInt()
                    lHour -= progressing[1].split(":")[0].toInt()
                }

                leftTime = lHour * 60 + lMinute
                while (true) {
                    if (progressing[2].toInt() - leftTime < 0) { // 다음 작업이 시작하기 전에 현재 진행중인 작업이 끝날 때
                        answer.add(progressing[0])
                        leftTime -= progressing[2].toInt()
                        if (waitStack.isNotEmpty()) {
                            progressing = waitStack.pop()

                        } else {
                            progressing = plans[i]
                            break
                        }
                    } else if (progressing[2].toInt() - leftTime == 0) { // 다음 작업이 시작할 때 현재 진행중인 작업이 끝날 때
                        answer.add(progressing[0])
                        progressing = plans[i]
                        break
                    } else { // 다음 작업이 시작할 때 현재 진행중인 작업이 끝나지 않았을 때
                        waitStack.push(arrayOf(progressing[0], plans[i][1], "${progressing[2].toInt() - leftTime}"))
                        progressing = plans[i]
                        break
                    }
                }
            }
        }

        if (!progressing.contentEquals(arrayOf(""))) answer.add(progressing[0])

        while (waitStack.isNotEmpty()) {
            answer.add(waitStack.pop()[0])
        }

        return answer.toTypedArray()
    }
}