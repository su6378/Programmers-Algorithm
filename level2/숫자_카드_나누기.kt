import java.util.*
import kotlin.math.max

fun main() {
    val arrayA = intArrayOf(14,35,119)
    val arrayB = intArrayOf(18,30,102)
    Solution().solution(arrayA, arrayB)
}

class Solution {
    fun solution(arrayA: IntArray, arrayB: IntArray): Int {

        var gcdA = if (arrayA.size == 1) arrayA[0] else gcd(arrayA[0],arrayA[1]) //원소가 1개면 그 친구가 최대공약수

        if (gcdA != 1){
            for (i in 2 until arrayA.size){
                gcdA = gcd(gcdA,arrayA[i])
                if (gcdA == 1) break
            }
        }

        var gcdB = if (arrayB.size == 1) arrayB[0] else gcd(arrayB[0],arrayB[1]) //원소가 1개면 그 친구가 최대공약수

        if (gcdB != 1){
            for (i in 2 until arrayB.size){
                gcdB = gcd(gcdB,arrayB[i])
                if (gcdB == 1) break
            }
        }

        var isDivde = true
        if (gcdA != 1){ // 최대공약수가 1이 아니라면 배열 B에 숫자 나누기
            for (i in arrayB.indices){
                if (arrayB[i] % gcdA == 0) { //나누어지지 않으면 빠져나가기
                    isDivde = false
                    break
                }
            }
        }

        if (!isDivde) gcdA = 1

        isDivde = true
        if (gcdB != 1){
            for (i in arrayA.indices){
                if (arrayA[i] % gcdB == 0) {
                    isDivde = false
                    break
                }
            }
        }

        if (!isDivde) gcdB = 1
        return if (max(gcdA,gcdB) == 1) 0 else max(gcdA,gcdB) //최대값이 1이면
    }
}

//최대 공약수
fun gcd(a: Int, b:Int): Int = if(b != 0) gcd(b, a % b) else a