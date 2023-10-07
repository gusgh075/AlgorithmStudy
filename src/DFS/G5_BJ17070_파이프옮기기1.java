package DFS;

import java.io.*;

public class G5_BJ17070_파이프옮기기1 {

    public static int[] x = {1, 1, 0};
    public static int[] y = {0, 1, 1};
    public static int[][] map;
    public static int n;
    public static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }
        DFS(0, 0, 0, 1);
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void DFS(int curx, int cury, int nextx, int nexty) {
        if (nextx >= n || nexty >= n)
            return;
        if (map[nextx][nexty] == 1)
            return;
        //대각선일시 양사이드 체크
        if ((nextx - curx == 1 && nexty - cury == 1)
                && (map[curx + 1][cury] == 1 || map[curx][cury + 1] == 1)) {
            return;
        }
        if (nextx == n - 1 && nexty == n - 1) {
            ans++;
            return;
        }

        //대각선
        DFS(nextx, nexty, nextx + x[1], nexty + y[1]);
        //가로
        if (nextx - curx == 1) {
            DFS(nextx, nexty, nextx + x[0], nexty + y[0]);
        }
        //세로
        if (nexty - cury == 1) {
            DFS(nextx, nexty, nextx + x[2], nexty + y[2]);
        }

    }


}
