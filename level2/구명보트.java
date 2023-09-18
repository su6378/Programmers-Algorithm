import java.util.*;

class Test {
    public static void main(String[] args) {
        int[] people = {10, 20, 30, 40, 50};
        int limit = 100;
        System.out.println(new Solution().solution(people, limit));
    }
}

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        if (people.length == 1) return 1;

        Arrays.sort(people);

        int end = 1;

        while (end < people.length) {
            if (people[0] + people[end] > limit) break;
            end++;
        }

        if (end-- == 1) return people.length;
        else if (end < people.length - 1) answer += people.length - 1 - end;

        for (int start = 0; start < people.length; start++) {
            boolean isFinish = false;

            while (true) {
                if (start >= end) {
                    if (start == end) answer++;
                    isFinish = true;
                    break;
                }

                if (people[start] + people[end] <= limit) {
                    end--;
                    answer++;
                    break;
                } else {
                    end--;
                    answer++;
                }
            }

            if (isFinish) break;
        }
        return answer;
    }
}