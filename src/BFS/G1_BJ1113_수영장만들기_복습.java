package BFS;

import java.io.*;
import java.util.*;

//3차원 물채우기 + bfs문제
public class G1_BJ1113_수영장만들기_복습 {
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] pool = new int[n][m];
        for (int i = 0; i < pool.length; i++) {
            String s = br.readLine();
            for (int j = 0; j < pool[i].length; j++) {
                pool[i][j] = s.charAt(j) - '0';
            }
        }
        int water = 0;
        for (int h = 1; h <= 9; h++) {   //물높이 1일때부터 시작
            boolean[][] visited = new boolean[n][m];
            for (int x = 0; x < n; x++) {   //모든 자리 조사
                for (int y = 0; y < m; y++) {
                    if (!visited[x][y] && pool[x][y] < h) {   //(x,y)를 방문하지 않았고, 해당 위치의 벽에 해당 높이의 물이 들어갈 수 있는가?
                        boolean isOuter = false;    //해당 위치에서 조사한 영역이 수영장 바깥과 연결되어있는지
                        Queue<int[]> queue = new ArrayDeque<>();    //BFS 시작
                        queue.add(new int[]{x, y});
                        visited[x][y] = true;
                        List<int[]> area = new ArrayList<>();    //area에는 방문했던 위치들이 기록된다
                        while (!queue.isEmpty()) {
                            int[] cur = queue.poll();
                            area.add(new int[]{cur[0], cur[1]});
                            for (int i = 0; i < 4; i++) {
                                int nx = cur[0] + dx[i];
                                int ny = cur[1] + dy[i];
                                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                                    isOuter = true;
                                    continue;
                                }
                                if (visited[nx][ny] || pool[nx][ny] >= h)
                                    continue;
                                queue.add(new int[]{nx, ny});
                                visited[nx][ny] = true;
                            }
                        }
                        if (!isOuter) {
                            for (int[] pos : area) {
                                water += (h - pool[pos[0]][pos[1]]);
                                pool[pos[0]][pos[1]] = h; //해당 위치에는 물이 채워졌기 때문에 물 높이만큼 수정해줘야됨
                            }
                        }
                    }
                }
            }
        }
        bw.write(String.valueOf(water));
        bw.flush();
    }
}
