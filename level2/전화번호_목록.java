import java.util.*;

public class Problem {

    public static void main(String[] args) {
        String[] phone_book = {"1195524421", "97674223", "119"};
        System.out.println(new Solution().solution(phone_book));
    }
}

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<String, Integer> map = new HashMap<>();

        Arrays.sort(phone_book); // 길이순으로 오름차순 정렬

        map.put(phone_book[0], 1); // 첫번째 값 map에 삽입

        for (int i = 1; i < phone_book.length; i++) {
            String num = phone_book[i];
            for (int j = 1; j <= num.length(); j++) { // 비교할 전화번호의 0부터 j까지의 문자열을 맵에서 조회하고 있으면 false return
                String prefix = num.substring(0, j);
                if (map.containsKey(prefix)) return false;
            }

            map.put(num, 1);
        }

        return answer;
    }
}


