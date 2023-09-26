import java.util.*;

class Test {
    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        new Solution().solution(cacheSize, cities);
    }
}

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayDeque<String> deque = new ArrayDeque<>();

        for (String city : cities) {
            boolean isFind = false;
            String data = "";
            int dSize = deque.size();

            for (int i = 0; i < dSize; i++) {
                String pollData = deque.pollFirst();
                if (pollData.equals(city.toLowerCase())) {
                    answer++;
                    data = pollData;
                    isFind = true;
                }else deque.offerLast(pollData);
            }

            if (isFind) {
                deque.offerLast(data);
            } else {
                if (cacheSize != 0){
                    if (deque.size() < cacheSize) deque.offerLast(city.toLowerCase());
                    else {
                        deque.pollFirst();
                        deque.offerLast(city.toLowerCase());
                    }
                }
                answer += 5;
            }
        }
        return answer;
    }
}