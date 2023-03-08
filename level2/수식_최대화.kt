import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs
import kotlin.math.max

fun main() {
    val expression = "100-200*300-500+20"
    Solution().solution(expression)
}

class Solution {
    lateinit var visited: BooleanArray //연산자 순서 중복체크 배열
    lateinit var exTemp: String // 계산하고 바뀐 계산식
    var answer: Long = 0

    fun solution(expression: String): Long {

        val opSet = HashSet<Char>()

        exTemp = expression

        for (char in expression) { //부호 집합 만들기
            when (char) {
                '*' -> opSet.add('*')
                '+' -> opSet.add('+')
                '-' -> opSet.add('-')
            }
        }

        visited = BooleanArray(opSet.size)
        makePriorityOp(0, opSet, CharArray(opSet.size), expression)
        return answer
    }

    //연산자 우선순위 만들기
    fun makePriorityOp(size: Int, opSet: HashSet<Char>, opArr: CharArray, expression: String) {
        if (size == opSet.size) { //연산자 우선순위가 만들어졌으면 해당 우선순위를 바탕으로 계산식 계산
            answer = max(answer, changeEx(opArr)) //최대값으로 갱신 시켜주기
            exTemp = expression
            return
        }

        for (i in opSet.indices) {
            if (!visited[i]) {
                visited[i] = true
                opArr[size] = opSet.elementAt(i)
                makePriorityOp(size + 1, opSet, opArr, expression)
                visited[i] = false
            }
        }
    }

    //우선순위를 바탕으로 계산식 계산하기
    fun changeEx(opArr: CharArray): Long {
        val numStack = Stack<Char>()
        val changeExpression = StringBuilder() //계산하고 바뀐 식
        var targetIdx = 0 //연산자 우선순위 인덱스
        var isCalculate: Boolean // 다음 숫자를 계산하면 되는지 체크
        var isPrior: Boolean //현재 계산할 연산자인지 체크
        var isMinus: Boolean //첫번째 계산할 숫자 음수 판단여부
        var isMinus2: Boolean //두번째 계산할 숫자 음수 판단연부
        var num1 = StringBuilder() //계산할 숫자 왼쪽
        var num2 = StringBuilder() //계산할 숫자 오른쪽

        while (true) {
            if (targetIdx == opArr.size) break //순서대로 연산이 끝나면 나오기

            //상태값 초기화 시키기
            isCalculate = false
            isMinus = false
            isMinus2 = false

            exTemp.forEachIndexed { i, char ->
                when (char) {
                    '*' -> {
                        isPrior = char == opArr[targetIdx]
                        if (isCalculate) { //앞에 나온 연산자가 우선순위에 있는 연산자라서 계산을 해야할 때
                            if (isPrior) { //현재 우선순위에 있는 연산자라면
                                isCalculate = true
                                val result = calculate(isMinus, isMinus2, num1.toString(), num2.toString(), opArr[targetIdx])
                                num1 = StringBuilder(result.toString())
                                num2.setLength(0)
                                isMinus = false
                                isMinus2 = false
                            } else { //현재 우선순위에 있는 연산자가 아니라면 계산식에 추가해주기
                                isCalculate = false
                                val result = calculate(isMinus, isMinus2, num1.toString(), num2.toString(), opArr[targetIdx])
                                changeExpression.append(result).append(char)
                                num1.setLength(0)
                                num2.setLength(0)
                                isMinus = false
                                isMinus2 = false
                            }
                        } else {
                            if (isPrior) { //해당 연산자가 우선순위에 있을 때
                                isCalculate = true
                                while (numStack.isNotEmpty()) num1.insert(0, numStack.pop())
                            } else {
                                isCalculate = false
                                while (numStack.isNotEmpty()) num1.insert(0, numStack.pop())
                                changeExpression.append(num1.toString())
                                num1.setLength(0)
                                changeExpression.append(char)
                            }
                        }
                    }

                    '-' -> {
                        isPrior = char == opArr[targetIdx]
                        if (isCalculate) { //앞에 나온 연산자가 우선순위에 있는 연산자라서 계산을 해야할 때
                            if (isPrior) { //현재 우선순위에 있는 연산자라면
                                isCalculate = true
                                val result = calculate(isMinus, isMinus2, num1.toString(), num2.toString(), opArr[targetIdx])
                                num1 = StringBuilder(result.toString())
                                num2.setLength(0)
                                isMinus = false
                                isMinus2 = false
                            } else { //현재 우선순위에 있는 연산자가 아니라면 계산식에 추가해주기
                                if (!exTemp[i-1].isDigit()) { //- 앞에 연산자가 나오면 해당 값은 -연산이 아닌 -숫자값임.
                                    isMinus2 = true
                                } else {
                                    isCalculate = false
                                    val result = calculate(isMinus, isMinus2, num1.toString(), num2.toString(), opArr[targetIdx])
                                    changeExpression.append(result).append(char)
                                    num1.setLength(0)
                                    num2.setLength(0)
                                    isMinus = false
                                    isMinus2 = false
                                }
                            }
                        } else {
                            if (isPrior) { //해당 연산자가 우선순위에 있을 때
                                if (numStack.isEmpty()) { //계산할 연산자가 없으면 해당 연산자는 -연산이 아닌 -값이다.
                                    isMinus = true
                                } else {
                                    isCalculate = true
                                    while (numStack.isNotEmpty()) num1.insert(0, numStack.pop())
                                }
                            } else {
                                if (numStack.isEmpty()) {
                                    isMinus = true
                                } else {
                                    isCalculate = false
                                    while (numStack.isNotEmpty()) num1.insert(0, numStack.pop())
                                    changeExpression.append(num1.toString())
                                    num1.setLength(0)
                                    changeExpression.append(char)
                                }
                            }
                        }
                    }

                    '+' -> {
                        isPrior = char == opArr[targetIdx]
                        if (isCalculate) { //앞에 나온 연산자가 우선순위에 있는 연산자라서 계산을 해야할 때
                            if (isPrior) { //현재 우선순위에 있는 연산자라면
                                isCalculate = true
                                val result = calculate(isMinus, isMinus2, num1.toString(), num2.toString(), opArr[targetIdx])
                                num1 = StringBuilder(result.toString())
                                num2.setLength(0)
                                isMinus = false
                                isMinus2 = false

                            } else { //현재 우선순위에 있는 연산자가 아니라면 계산식에 추가해주기
                                isCalculate = false
                                val result = calculate(isMinus, isMinus2, num1.toString(), num2.toString(), opArr[targetIdx])
                                changeExpression.append(result).append(char)
                                num1.setLength(0)
                                num2.setLength(0)
                                isMinus = false
                                isMinus2 = false
                            }
                        } else {
                            if (isPrior) { //해당 연산자가 우선순위에 있을 때
                                isCalculate = true
                                while (numStack.isNotEmpty()) num1.insert(0, numStack.pop())
                            } else {
                                isCalculate = false
                                while (numStack.isNotEmpty()) num1.insert(0, numStack.pop())
                                changeExpression.append(num1.toString())
                                num1.setLength(0)
                                changeExpression.append(char)
                            }
                        }
                    }

                    else -> { //숫자
                        if (isCalculate) num2.append(char) //해당 우선순위의 연산자가 발견되면 계산할 숫자로 판단
                        else numStack.push(char) //계산식에 넣기
                    }
                }
            }

            if (isCalculate) { // 연산해야할 연산자가 마지막 연산에 있으면 연산을 해주고 계산식에 반영해준다.
                val result = calculate(isMinus, isMinus2, num1.toString(), num2.toString(), opArr[targetIdx])
                changeExpression.append(result)
                num1.setLength(0)
                num2.setLength(0)
            }
            println(changeExpression)
            if (numStack.isNotEmpty()) { //마지막에 숫자로 식이 끝나면 스택에 있는 숫자들도 계산해줘야한다.
                while (numStack.isNotEmpty()) num1.insert(0, numStack.pop())
                changeExpression.append(num1)
                num1.setLength(0)
            }

            exTemp = changeExpression.toString()
            changeExpression.setLength(0)
            targetIdx++
        }
        return abs(exTemp.toLong())
    }

    //연산자 계산
    fun calculate(isMinus: Boolean, isMinus2: Boolean, num1: String, num2: String, op: Char): Long {
        var minus = if (isMinus) -1 else 1
        var minus2 = if (isMinus2) -1 else 1

        return when (op) {
            '*' -> num1.toLong() * minus * num2.toLong() * minus2
            '+' -> (num1.toLong() * minus) + (num2.toLong() * minus2)
            else -> (num1.toLong() * minus) - (num2.toLong() * minus2)
        }
    }
}
