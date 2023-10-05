import java.util.*

fun main() {
    SolutionKotlin().solution(beginning = arrayOf(intArrayOf(0, 1, 0, 0, 0), intArrayOf(1, 0, 1, 0, 1), intArrayOf(0, 1, 1, 1, 0), intArrayOf(1, 0, 1, 1, 0), intArrayOf(0, 1, 0, 1, 0)),
            target = arrayOf(intArrayOf(0, 0, 0, 1, 1), intArrayOf(0, 0, 0, 0, 1), intArrayOf(0, 0, 1, 0, 1), intArrayOf(0, 0, 0, 1, 0), intArrayOf(0, 0, 0, 0, 1)))
}

data class Coin(val state: String, val count: Int, val commandList: ArrayList<Int>)

var row = 0
var col = 0
lateinit var beginSb: StringBuilder
lateinit var targetSb: StringBuilder
lateinit var stateMap: HashMap<String, Int>

class SolutionKotlin {
    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        row = beginning.size
        col = beginning[0].size
        beginSb = StringBuilder()
        targetSb = StringBuilder()
        stateMap = HashMap()

        for (i in 0 until row) {
            for (j in 0 until col) {
                beginSb.append(beginning[i][j])
            }
        }

        for (i in 0 until row) {
            for (j in 0 until col) {
                targetSb.append(target[i][j])
            }
        }

        return bfs()
    }
}

fun bfs(): Int {
    val queue: Queue<Coin> = LinkedList()
    val commandList = ArrayList<Int>()

    stateMap[beginSb.toString()] = 1
    queue.add(Coin(beginSb.toString(), 0, commandList))

    while (!queue.isEmpty()) {
        val now = queue.poll()
        if (now.state == targetSb.toString()) return now.count
        for (i in 0 until row + col) {
            val tempList = ArrayList(now.commandList)

            if (tempList.contains(i)) continue  // 이미 실행한 명령이면 pass

            val change  = reverseCoin(now.state, i)

            if (stateMap.containsKey(change)) continue  // 이미 나온 상태이면 pass

            stateMap[change] = 1
            tempList.add(i)
            queue.add(Coin(change, now.count + 1, tempList))
        }
    }

    return -1
}

fun reverseCoin(state: String, command: Int): String {
    val changeSb = StringBuilder()

    if (command < row) { // 행 뒤집기
        for (i in 0 until row * col) {
            if (i >= command * col && i < (command + 1) * col) {
                if (state[i] == '1') changeSb.append('0') else changeSb.append('1')
            } else changeSb.append(state[i])
        }
    } else { // 열 뒤집기
        for (i in 0 until row * col) {
            if (i % col == (command - 1) % col) {
                if (state[i] == '1') changeSb.append('0') else changeSb.append('1')
            } else changeSb.append(state[i])
        }
    }

    return changeSb.toString()
}