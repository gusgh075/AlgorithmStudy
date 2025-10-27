package Mixed;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/*
1. 백조가 있는 공간을 따로 표시해둠 (첫번째, 두번째 구분 o)
2. 날짜별로 한번에 녹임
3. 녹이면서 백조가 있는 물가가 새로운 물가를 만나게 되면 표시해줌
3-1. 표시하면서 다른 백조가 있는 호수와 맞닿게 되면 표시해줌

이거 솔직히 말하면 구조화가 덜 되어있어서 문제 푸는데 너무 오래 걸린 것 같음
풀기 전에 구현순서 딱 정하고, 객체지향적으로 메서드도 딱 구분하고 했으면 훨씬 조금 걸렸을 것 같음
막힌 부분은 spreadL에서 0인 부분의 인근도 조사를 해야됐는데 2,3에서 시작한다고 단정짓고 함
결론적으로, 머릿속게 그림을 구체적으로 못그려서 그런듯
 */
public class P5_BJ3197_백조의호수_Hash_BFS {
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static class Pair {
        Point pnt;
        int cnt;

        public Pair(Point pnt, int cnt) {
            this.pnt = new Point(pnt.x, pnt.y);
            this.cnt = cnt;
        }
    }

    public static boolean spreadL(int[][] map, Point now) {
        int cur = map[now.y][now.x];
        int r = map.length;
        int c = map[0].length;
        Queue<Point> lQ = new ArrayDeque<>();
        lQ.add(new Point(now.x, now.y));
        while (!lQ.isEmpty()) {
            Point n = lQ.poll();
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (ny >= r || nx >= c || ny < 0 || nx < 0) continue;
                if (map[ny][nx] == map[n.y][n.x] || map[ny][nx] == 7) continue;
                if (isAdj(map, n, new Point(nx, ny))) return true;
                if ((map[n.y][n.x] == 0 && map[ny][nx] == 2) || (map[n.y][n.x] == 0 && map[ny][nx] == 3)) {
                    map[n.y][n.x]=map[ny][nx];
                    lQ.add(new Point(n.x,n.y));
                }
                if ((map[n.y][n.x] == 2 && map[ny][nx] == 0) || (map[n.y][n.x] == 3 && map[ny][nx] == 0)) {
                    map[ny][nx]=map[n.y][n.x];
                    lQ.add(new Point(nx,ny));
                }
            }
        }
        return false;
    }

    public static boolean isAdj(int[][] map, Point now, Point nxt) {
        return (map[now.y][now.x] == 2 && map[nxt.y][nxt.x] == 3) || (map[now.y][now.x] == 3 && map[nxt.y][nxt.x] == 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int r = input[0];
        int c = input[1];
        int[][] map = new int[r][c];   //X : 7 / L : 2,3 / . : 0
        int nowL = 2;
        Queue<Point> lQ = new ArrayDeque<>();
        Queue<Pair> q = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                char cur = s.charAt(j);
                if (cur == '.') {
                    map[i][j] = 0;
                    q.add(new Pair(new Point(j, i), 0));
                } else if (cur == 'L') {
                    map[i][j] = nowL;
                    nowL++;
                    lQ.add(new Point(j, i));
                    q.add(new Pair(new Point(j, i), 0));
                } else if (cur == 'X') map[i][j] = 7;
                else throw new IllegalArgumentException("잘못된 문자 입력: " + cur);
            }
        }
        //백조 인근 물들을 표시
        while (!lQ.isEmpty()) {
            Point cur = lQ.poll();
            if (spreadL(map, cur)) {
                System.out.println(0);
                return;
            }
        }
        int flag = 1;
        while (!q.isEmpty()) {
            Pair now = q.poll();
            Point cur = now.pnt;
            if (flag == now.cnt) {
                for (Pair p : q) {
                    if (p.cnt != flag) break;
                    Point cp = p.pnt;
                    if (spreadL(map, new Point(cp.x, cp.y))) {
                        System.out.println(p.cnt);
                        return;
                    }
                }
                flag++;
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (ny >= r || nx >= c || ny < 0 || nx < 0) continue;
                if (map[ny][nx] == 7) {
                    map[ny][nx] = map[cur.y][cur.x];
                    q.add(new Pair(new Point(nx, ny), now.cnt + 1));
                }
            }
        }
    }
}
