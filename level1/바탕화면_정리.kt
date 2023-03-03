import kotlin.math.*

fun main() {
    val wallpaper = arrayOf("..........", ".....#....", "......##..", "...##.....", "....#.....")
    Solution().solution(wallpaper)
}

class Solution {
    fun solution(wallpaper: Array<String>): IntArray {
        var lux = Integer.MAX_VALUE
        var luy = Integer.MAX_VALUE
        var rdx = -1
        var rdy = -1

        for (i in wallpaper.indices){
            for (j in wallpaper[i].indices){
                if (wallpaper[i][j] == '#'){ //파일을 찾았을 때
                    lux = min(lux,i)
                    luy = min(luy,j)
                    rdx = max(rdx,i+1)
                    rdy = max(rdy,j+1)
                }
            }
        }
        return intArrayOf(lux,luy,rdx,rdy)
    }
}