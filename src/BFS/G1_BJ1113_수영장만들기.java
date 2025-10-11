package BFS;

import java.io.*;
import java.util.*;

public class G1_BJ1113_수영장만들기 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] maze = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        int water = 0;

        for (int h = 1; h <= 9; h++) {
            boolean[][] visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && maze[i][j] < h) {
                        Queue<int[]> q = new ArrayDeque<>();
                        q.add(new int[]{i, j});
                        visited[i][j] = true;

                        boolean isOuter = false;
                        List<int[]> area = new ArrayList<>();

                        while (!q.isEmpty()) {
                            int[] cur = q.poll();
                            area.add(cur);

                            for (int d = 0; d < 4; d++) {
                                int nx = cur[0] + dx[d];
                                int ny = cur[1] + dy[d];

                                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                                    isOuter = true;
                                    continue;
                                }

                                if (visited[nx][ny] || maze[nx][ny] >= h) continue;

                                visited[nx][ny] = true;
                                q.add(new int[]{nx, ny});
                            }
                        }

                        if (!isOuter) {
                            for (int[] pos : area) {
                                water += (h - maze[pos[0]][pos[1]]);
                                maze[pos[0]][pos[1]] = h; // 물이 찼으므로 실제 높이 갱신
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
