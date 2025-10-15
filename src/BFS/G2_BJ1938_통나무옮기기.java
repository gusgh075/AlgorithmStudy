package BFS;

import java.io.*;
import java.util.*;

/*
입력값
0 : 아무 것도 없음
1 : 잘려지지 않은 나무
B : 2
E : 3
B를 E로 옮겨야됨
! 통나무의 길이는 항상 3
! B와 E의 개수는 같음 => 통나무의 개수는 여러개일 수 있음
움직이는 방법
위, 아래, 왼쪽, 오른쪽, 중심점을 기준으로 90도 회전
=> 이때 움직이는 방향에 다른 나무(1)가 없어야 함
! 통나무는 대각선이 될 수 없음
! 회전시 3*3범위에 나무가 있어서는 안됨
 */
public class G2_BJ1938_통나무옮기기 {
    static int[] dx = new int[]{1, 0, -1, 0, 1, 1, -1, -1};
    static int[] dy = new int[]{0, 1, 0, -1, 1, -1, -1, 1};

    public static class Log {
        int x;
        int y;
        boolean isUp;

        public Log(int x, int y, boolean isUp) {
            this.x = x;
            this.y = y;
            this.isUp = isUp;
        }

        @Override
        public boolean equals(Object obj) {
            if (this.getClass() != obj.getClass()) return false;
            return
                    (((Log) obj).x == this.x) &&
                            (((Log) obj).y == this.y) &&
                            (((Log) obj).isUp == this.isUp);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, isUp);
        }
    }

    public static class Pair {
        Log log;
        int cnt;

        public Pair(Log log, int cnt) {
            this.log = new Log(log.x, log.y, log.isUp);
            this.cnt = cnt;
        }
    }

    public static boolean canRotate(int[][] map, Log log, int n) {
        int x = log.x;
        int y = log.y;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = x + j;
                int ny = y + i;
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) return false;
                if (map[ny][nx] == 1) return false;
            }
        }
        return true;
    }

    public static boolean canMove(int[][] map, Log log) {
        int x = log.x;
        int y = log.y;
        if(x<0||y<0||x>=map.length||y>=map.length) return false;
        if (log.isUp) {
            for (int i = -1; i <= 1; i++) {
                int ny = y + i;
                if (ny < 0 || ny >= map.length) return false;
                if (map[ny][x] == 1) return false;
            }
        } else {
            for (int i = -1; i <= 1; i++) {
                int nx = x + i;
                if (nx < 0 || nx >= map.length) return false;
                if (map[y][nx] == 1) return false;
            }
        }

        return true;
    }

    public static boolean isEnd(int[][] map, Log log, int n) {
        int x = log.x;
        int y = log.y;
        int[] range = {-1, 0, 1};
        for (int i : range) {
            int nx = log.isUp ? x : x + i;
            int ny = log.isUp ? y + i : y;
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) return false;
            if (map[ny][nx] != 3) return false;
        }
        return true;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int ans = -1;
        //map 입력
        int[][] map = new int[n][n];
        List<Log> logList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                if (input.charAt(j) == 'B') {
                    map[i][j] = 0;
                    logList.add(new Log(j, i, true));
                } else if (input.charAt(j) == 'E') map[i][j] = 3;
                else map[i][j] = input.charAt(j) - '0';
            }
        }
        //통나무 위치 탐색
        int tmpX = 0;
        int tmpY = 0;
        int tmp = logList.get(0).y;
        boolean tmpIsUp = false;
        for (Log log : logList) {
            if (tmp != log.y) tmpIsUp = true;
            tmpX += log.x;
            tmpY += log.y;
        }
        Log log = new Log(tmpX / 3, tmpY / 3, tmpIsUp);
        //이제 통나무 위치를 구하고, map을 그렸다.
        //이 다음에는 통나무들을 5가지 이동방식에 따라 옮겨야한다.
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(log, 0));
        Set<Log> hash = new HashSet<>();
        hash.add(log);
        while (!q.isEmpty()) {
            Pair p = q.poll();
            Log cur = p.log;
            if (isEnd(map, cur, n)) {
                ans = p.cnt;
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                Log next = new Log(nx, ny, cur.isUp);
                if (hash.contains(next)) continue;
                if (!canMove(map, next)) continue;
                q.add(new Pair(next, p.cnt + 1));
                hash.add(next);
            }
            //회전할때
            if (canRotate(map, cur, n)) {
                Log next = new Log(cur.x, cur.y, !cur.isUp);
                if(!hash.contains(next)) {
                    q.add(new Pair(next, p.cnt + 1));
                    hash.add(next);
                }
            }
        }
        if (ans == -1) bw.write("0");
        else bw.write(Integer.toString(ans));
        bw.flush();
    }
}
