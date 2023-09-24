import java.util.*;

class Test {
    public static void main(String[] args) {
        int n = 1000000000;
        System.out.println((new Solution().solution(n)));
    }
}

public class Solution {
    public int solution(int n) {
        int ans = 0;

        while (n > 0) {
            if (n % 2 == 0) n /= 2;
            else {
                n--;
                ans++;
            }
        }

        return ans;
    }
}