class Test {
    public static void main(String[] args) {
        int n = 16, t = 16, m = 2, p = 2;
        new Solution().solution(n, t, m, p);
    }
}

class Solution {
    public String solution(int n, int t, int m, int p) {
        int num = 0;
        int turn = 1;
        int tube;
        StringBuilder answer = new StringBuilder();

        // 튜브가 마지막 순서일 때 체크
        if (m != p) tube = p;
        else tube = 0;

        while (true) {
            if (answer.length() == t) break;

            String strNum = Integer.toUnsignedString(num, n).toUpperCase();

            for (int i = 0; i < strNum.length(); i++) {
                if (turn % m == tube) {
                    answer.append(strNum.charAt(i));
                }

                if (answer.length() == t) break;

                turn++;
            }

            num++;
        }

        return answer.toString();
    }
}