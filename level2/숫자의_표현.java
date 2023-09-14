class Test {
    public static void main(String[] args) {
        int n = 15;
        new Solution().solution(n);
    }
}

class Solution {
    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int num = i;
            int sum = 0;
            
            while (num <= n){
                sum += num;

                if (sum == n) {
                    answer++;
                    break;
                }else if(sum > n) break;
                num++;
            }
        }
        return answer;
    }
}