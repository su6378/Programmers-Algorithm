package com.example.test

class Solution {
    fun solution(new_id: String): String {
    var answer = new_id

    answer = oneStep(answer)
    answer = twoStep(answer)
    answer = threeStep(answer)
    answer = fourStep(answer)
    answer = fiveStep(answer)
    answer = sixStep(answer)
    answer = sevenStep(answer)

    return answer
}

fun oneStep(str: String): String {
    return str.lowercase()
}

fun twoStep(str: String): String {
    var sb = StringBuffer()

    str.forEach {
        if((it in 'a'..'z') || (it in '0'..'9') || it=='-' || it=='_' || it=='.') {
            sb.append(it)
        }
    }
    return sb.toString()
}

fun threeStep(str: String): String {
    var three_str = str
    while (three_str.contains("..")) three_str = three_str.replace("..", ".")
    return three_str
}

fun fourStep(str:String) :String{
    var four_str = str
    if (four_str.isNotEmpty()) {
        if (four_str.first() == '.') four_str = four_str.substring(1, four_str.length)
    }
    if (four_str.isNotEmpty()) {
        if (four_str.last() == '.') four_str = four_str.substring(0, four_str.length - 1)
    }
    return four_str
}

fun fiveStep(str:String) : String{
    var five_str = str
    if(five_str.isEmpty()){
        five_str = "a"
    }
    return five_str
}

fun sixStep(str:String) : String{
    var six_str = str
    if(six_str.length >= 16){
        six_str = six_str.substring(0,15)
        if(six_str.last() == '.') six_str= six_str.substring(0,six_str.length-1)
    }
    return six_str
}

fun sevenStep(str:String) : String{
    var seven_str = str
    if(seven_str.length <= 2){
            while(true){
                if(seven_str.length == 3){
                    break
                }
                seven_str += seven_str.last()
            }
    }
    return seven_str
}
}