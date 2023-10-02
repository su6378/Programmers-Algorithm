class Test {
    public static void main(String[] args) {
        String dirs = "LULLLLLLU";
        new Solution().solution(dirs);
    }
}

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int nowX = 5;
        int nowY = 5;
        boolean[][][][] road = new boolean[11][11][11][11];

        for (int i = 0; i < dirs.length(); i++) {
            switch (dirs.charAt(i)) {
                case 'U':
                    if (nowY + 1 <= 10) {
                        if (!road[nowY][nowX][nowY + 1][nowX]) {
                            road[nowY][nowX][nowY + 1][nowX] = true;
                            road[nowY + 1][nowX][nowY][nowX] = true;
                            answer++;
                        }
                        nowY++;
                    }
                    break;

                case 'D':
                    if (nowY - 1 >= 0) {
                        if (!road[nowY][nowX][nowY - 1][nowX]) {
                            road[nowY][nowX][nowY - 1][nowX] = true;
                            road[nowY - 1][nowX][nowY][nowX] = true;
                            answer++;
                        }
                        nowY--;
                    }
                    break;

                case 'R':
                    if (nowX + 1 <= 10) {
                        if (!road[nowY][nowX][nowY][nowX + 1]) {
                            road[nowY][nowX][nowY][nowX + 1] = true;
                            road[nowY][nowX + 1][nowY][nowX] = true;
                            answer++;
                        }
                        nowX++;
                    }
                    break;

                default:
                    if (nowX - 1 >= 0) {
                        if (!road[nowY][nowX][nowY][nowX - 1]) {
                            road[nowY][nowX][nowY][nowX - 1] = true;
                            road[nowY][nowX - 1][nowY][nowX] = true;
                            answer++;
                        }
                        nowX--;
                    }
                    break;
            }
        }

        return answer;
    }
}