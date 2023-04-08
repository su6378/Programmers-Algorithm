import java.awt.Point

fun main() {
    Solution().solution(board = arrayOf("O.X", ".O.", "..X"))
}

class Solution {
    fun solution(board: Array<String>): Int {
        var answer: Int = -1

        // 같은 표시가 되었는지 체크하는 boolean
        var oState = false
        var xState = false

        var oCount = 0
        var xCount = 0

        for (i in board.indices){
            for (j in board[i].indices){
                if (board[i][j] == 'O'){
                    if (!oState) oState = checkTriple(Point(j,i),'O',board)
                    oCount++
                }else if (board[i][j] == 'X'){
                    if (!xState) xState = checkTriple(Point(j,i),'X',board)
                    xCount++
                }
            }
        }
        if (oState && xState) answer = 0 // 둘다 3개 연속인 경우에는 O가 먼저 완성했기 때문에 끝나야하는데 게임이 안 끝남 -> 0
        else if(oState && !xState){
            // O가 3개를 완성시켜 게임이 끝남 -> 1
            answer = if (oCount > xCount && oCount - xCount == 1) 1
            else if (oCount < xCount && xCount - oCount == 1) 1
            else 0 // O 개수가 X보다 많거나
        } else if (!oState && xState) {
            answer = if (oCount == xCount) 1 // X가 3개를 완성시켜 게임이 끝남 -> 1
            else 0
        }else{ // 둘 다 연속된 3개로 이어지지 않은 경우
            if (oCount == xCount) answer = 1
            else if(oCount - xCount == 1) answer = 1
            else answer = 0
        }
        return answer
    }

    // 같은 표시가 연속으로 3개가 있는지 제크 함수
    fun checkTriple(point: Point, turn : Char, board: Array<String>) : Boolean{
        when(point){
            Point(0,0) ->{
                if (board[0][1] == turn && board[0][2] == turn) return true
                else if(board[1][0] == turn && board[2][0] == turn) return true
                else if(board[1][1] == turn && board[2][2] == turn) return true
            }
            Point(1,0) -> {
                if (board[1][1] == turn && board[2][1] == turn) return true
            }
            Point(2,0) -> {
                if (board[1][2] == turn && board[2][2] == turn) return true
                if (board[1][1] == turn && board[2][0] == turn) return true
            }
            Point(0,1) -> {
                if (board[1][1] == turn && board[1][2] == turn) return true
            }
            Point(0,2) ->{
                if (board[2][1] == turn && board[2][2] == turn) return true
            }
        }
        return false
    }
}