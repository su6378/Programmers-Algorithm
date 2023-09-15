class Test {
    public static void main(String[] args) {
        int n = 15;
        new Solution().solution(n);
    }
}

class Solution {
    public int solution(int n) {
        int answer = 0;
        String binaryN = Integer.toBinaryString(n);
        int count = 0;

        for (int i = 0; i < binaryN.length(); i++) {
            if (binaryN.charAt(i) == '1') count++;
        }


        while (true) {
            int nCount = 0;
            binaryN = Integer.toBinaryString(++n);

            for (int i = 0; i < binaryN.length(); i++) {
                if (binaryN.charAt(i) == '1') nCount++;
            }

            if (count == nCount) break;
        }
        return n;
    }
}