package Mixed;

import java.awt.*;
import java.io.*;
import java.util.*;

public class P5_BJ3197_백조의호수_Hash_BFS_2 {
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static class Pair {
        Point pos;
        int cnt;

        public Pair(Point pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }
    }

    public static boolean isInMap(int x, int y, int r, int c) {
        return x < c && y < r && x >= 0 && y >= 0;
    }

    /**
     * 백조 포함 물웅덩이를 표시 및 hashset에 추가
     *
     * @param map
     * @param start
     * @param set
     */
    public static void birdRoadSpread(int[][] map, Point start, Set<Point> set) {
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
                if (map[ny][nx] == 0) {
                    map[ny][nx] = 2;
                    set.add(new Point(nx, ny));
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    /**
     * 초기 큐 세팅
     *
     * @param map
     * @param start
     * @param end
     * @return
     */
    public static Queue<Pair> setInit(int[][] map, Set<Point> start, Set<Point> end) {
        return null;
    }

    /**
     * nxt의 물을 녹이고 백조 포함 물인지, 일반 물인지 확인하기
     *
     * @param map
     * @param now
     * @param nxt
     * @param start
     * @param end
     * @return
     */
    public static void meltWater(int[][] map, Point now, Point nxt, Set<Point> start, Set<Point> end) {

    }

    /**
     * BFS를 통해 물웅덩이 시간별로 녹이는 거 확인
     *
     * @param map
     * @param start
     * @param end
     * @return
     */
    public static int BFS(int[][] map, Set<Point> start, Set<Point> end) {
        Queue<Pair> q = new ArrayDeque<>();
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int r = input[0];
        int c = input[1];
        int[][] map = new int[r][c];
        Set<Point> start = new HashSet<>();
        Set<Point> end = new HashSet<>();
        boolean isStart = true;
        for (int i = 0; i < r; i++) {
            map[i] = Arrays.stream(br.readLine().
                            replace("L", String.valueOf(2)).
                            replace("X", String.valueOf(-1)).split("")).
                    mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 2) {
                    if (isStart)
                        start.add(new Point(j, i));
                    if (!isStart)
                        end.add(new Point(j, i));
                    isStart = !isStart;
                }
            }
        }
        for (Point point : start) {
            birdRoadSpread(map, point, start);
        }
        for (Point point : end) {
            birdRoadSpread(map, point, end);
        }


    }
}
