import java.util.*;

public class Problem {

    public static void main(String[] args) {
        new Solution().solution(new int[]{1, 2, 3, 9, 10, 12}, 7);
    }
}

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }

        while (true) {
            if (pq.peek() >= K) break; // 모든 음식의 스코빌 지수가 K이상이면 break

            if (pq.size() == 1 && pq.peek() < K) { // 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우
                answer = -1;
                break;
            }

            int min = pq.poll(); // 가장 맵지 않은 음식의 스코빌 지수
            int min2 = pq.poll(); // 두 번째로 맵지 않은 음식의 스코빌 지수
            int mix = min + (min2 * 2);

            pq.add(mix);
            answer++;
        }
        return answer;
    }
}