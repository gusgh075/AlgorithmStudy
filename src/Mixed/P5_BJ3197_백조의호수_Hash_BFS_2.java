package Mixed;

import java.awt.*;
import java.io.*;
import java.util.*;

public class P5_BJ3197_백조의호수_Hash_BFS_2 {
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static boolean isInMap(int x, int y, int r, int c) {
        return x < c && y < r && x >= 0 && y >= 0;
    }

    public static void birdRoadSpread(int[][] map, Point start, Set<Point> birdWater) {
        if (map[start.y][start.x] != 2) return;
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);
        int r = map.length;
        int c = map[0].length;
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (!isInMap(nx, ny, r, c)) continue;
                if (map[ny][nx] == 2||map[ny][nx]==-1) continue;
                q.add(new Point(nx, ny));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int r = input[0];
        int c = input[1];
        int[][] map = new int[r][c];
        Set<Point> birdWater = new HashSet<>();
        for (int i = 0; i < r; i++) {
            map[i] = Arrays.stream(br.readLine().
                            replace("L", String.valueOf(2)).
                            replace("X", String.valueOf(-1)).split("")).
                    mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 2) birdWater.add(new Point(j, i));
            }
        }

    }
}
