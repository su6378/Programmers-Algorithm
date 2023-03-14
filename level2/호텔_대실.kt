import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*

fun main() {
    val book_time = arrayOf(
            arrayOf("15:00", "17:00"),
            arrayOf("16:40", "18:20"),
            arrayOf("14:20", "15:20"),
            arrayOf("14:10", "19:20"),
            arrayOf("18:20", "21:20")
    )
    Solution().solution(book_time)
}

class Solution {
    fun solution(book_time: Array<Array<String>>): Int {

        book_time.sortWith(compareBy<Array<String>> { it[0] }.thenBy { it[1] }) //대실 시작 시간으로 먼저 정렬하고 같은 경우 대실 종료시간으로 정렬
        val roomList = ArrayList<Int>() //방 리스트

        for (bookTime in book_time) {
            val enterTime = bookTime[0].replace(":", "").toInt() //해당 방에 입실시간
            val exitTime = bookTime[1].replace(":", "").toInt() //해당 방에 퇴실시간

            if (roomList.isEmpty()) if (((exitTime / 10) % 10) + 1 == 6) roomList.add((exitTime / 100) * 100 +100 + exitTime % 10) else roomList.add(exitTime + 10)//방이 비어있다면 퇴실 시간 + 청소 시간 시간을 넣어주기
            else {
                roomList.sort() //시간순으로 정렬
                var isIncrease = true //방의 개수를 증가시킬건지 확인 boolean
                val checkTime = if (((exitTime / 10) % 10) + 1 == 6) (exitTime / 100) * 100 + 100 + exitTime % 10 else exitTime + 10 //현재 사용하고 있는 방이 퇴실하고 나서 청소가 끝난 시간
                for (i in roomList.indices) {
                    if (roomList[i] <= enterTime) {
                        roomList[i] = checkTime
                        isIncrease = false
                        break
                    }
                }
                if (isIncrease) roomList.add(checkTime)
            }
        }
        return roomList.size
    }
}