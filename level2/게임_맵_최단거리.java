import java.awt.*;
import java.util.*;

public class Problem {

    public static void main(String[] args) {
        new Solution().solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}});
    }
}

class Solution {

    boolean[][] visited;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    int answer = 1;
    public int solution(int[][] maps) {

        visited = new boolean[maps.length][maps[0].length];

        if (!bfs(maps)) answer = -1;
        return answer;
    }

    public boolean bfs(int[][] maps) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int qSize = queue.size();
            answer++;

            for (int i = 0; i < qSize; i++) {
                Point point = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int pointX = point.x + dx[j];
                    int pointY = point.y + dy[j];

                    if (pointX >= 0 && pointX < visited[0].length && pointY >= 0 && pointY < visited.length && !visited[pointY][pointX] && maps[pointY][pointX] == 1) {
                        if(pointX == visited[0].length-1 && pointY == visited.length-1){
                            return true;
                        }else{
                            queue.add(new Point(pointX,pointY));
                            visited[pointY][pointX] = true;
                        }
                    }
                }

            }
        }
        return false;
    }
}