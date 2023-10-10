package BruteForce;

import java.io.*;

//먼저 BruteForce임에 단번에 알아봤다. 후후
//
//        1. 모든 사각형에 접근
//        2. 해당 사각형부터 가로, 세로 중 끝까지 적은 위치를 탐색
//        3. 해당 위치로 사각형을 점차 넓혀나가며 탐색
//
//        일단 문제에 있어, for문을 중첩하다보니, 변수 설정을 하는데 깔꼼쓰하지 못했다…
//
//        그것 말고는 쉬웠던 문제였다
public class S3_BJ1051_숫자정사각형 {
    public static void sol() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int[][] square = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] s2 = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                square[i][j] = Integer.parseInt(s2[j]);
            }
        }
        int ans = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (n - i < m - j) {
                    for (int k = 1; k < n - i; k++) {
                        if (square[i][j] == square[i + k][j] &&
                                square[i][j + k] == square[i + k][j + k] &&
                                square[i][j] == square[i + k][j + k]) {
                            ans = Math.max(ans, (int) Math.pow(k+1,2));
                        }
                    }
                } else {
                    for (int k = 1; k < m - j; k++) {
                        if (square[i][j] == square[i + k][j] &&
                                square[i][j + k] == square[i + k][j + k] &&
                                square[i][j] == square[i + k][j + k]) {
                            ans = Math.max(ans, (int) Math.pow(k+1, 2));
                        }
                    }
                }

            }
        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
