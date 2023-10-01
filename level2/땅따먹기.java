import java.util.Arrays;

class Test {
    public static void main(String[] args) {
        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};
        new Solution().solution(land);
    }
}

class Solution {
    int solution(int[][] land) {
        int[][] dp = new int[land.length + 1][4];

        for (int i = 0; i < 4; i++) {
            dp[0][i] = land[0][i];
        }

        if (land.length > 1) {
            for (int i = 1; i < land.length; i++) {
                for (int j = 0; j < 4; j++) {
                    switch (j) {
                        case 0:
                            dp[i][0] = land[i][0] + Math.max(dp[i - 1][1], Math.max(dp[i - 1][2], dp[i - 1][3]));
                            break;
                        case 1:
                            dp[i][1] = land[i][1] + Math.max(dp[i - 1][0], Math.max(dp[i - 1][2], dp[i - 1][3]));
                            break;
                        case 2:
                            dp[i][2] = land[i][2] + Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][3]));
                            break;
                        default:
                            dp[i][3] = land[i][3] + Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
                            break;
                    }
                }
            }
        }

        return Arrays.stream(dp[land.length - 1]).max().getAsInt();
    }
}