import java.awt.*;
import java.io.*;
import java.util.*;

/*
land[][] 중 꼭짓점 하나가 겹치는 정사각형 2개를 고른다
그리고 이 정사각형 2개 내부의 합은 같아야 한다
크기는 달라도 된다
이때 경우의 수는?

시간복잡도 O(n^4) => n의 최대값은 50

gpt의 도움 ㅇ => for문을 얼마나 돌릴 것인지 + 누적합 개념 도움받음

느낀점 : 전처리 과정(ex.누적합)이 중요하다! 데이터를 어떻게 전처리 할것인지 생각해보자.
 */
public class G1_BJ1184_귀농 {
    public static void main(String[] args) throws IOException {
        int ans = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] pSum = new int[n][n];
        int[][] land = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                land[i][j] = Integer.parseInt(input[j]);
            }
        }
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                pSum[y][x] = land[y][x];
                if (y > 0) pSum[y][x] += pSum[y - 1][x];
                if (x > 0) pSum[y][x] += pSum[y][x - 1];
                if (y > 0 && x > 0) pSum[y][x] -= pSum[y - 1][x - 1];
            }
        }
        for (int y = 1; y < n; y++) {
            for (int x = 1; x < n; x++) {
                //왼쪽 위
                HashMap<Integer, Integer> mapLU = new HashMap<>();
                for (int dx = 1; x - dx >= 0; dx++) {
                    for (int dy = 1; y - dy >= 0; dy++) {
                        int x1 = x - dx;
                        int y1 = y - dy;
                        int x2 = x - 1;
                        int y2 = y - 1;
                        int sum = getSum(pSum, y1, x1, y2, x2);
                        mapLU.put(sum, mapLU.getOrDefault(sum, 0) + 1);
                    }
                }

                //오른쪽 아래
                for (int dx = 1; x + dx <= n; dx++) {
                    for (int dy = 1; y + dy <= n; dy++) {
                        int x1 = x;
                        int y1 = y;
                        int x2 = x + dx - 1;
                        int y2 = y + dy - 1;
                        int sum = getSum(pSum, y1, x1, y2, x2);
                        if (mapLU.containsKey(sum)) ans += mapLU.get(sum);
                    }
                }

                //오른쪽 위
                HashMap<Integer, Integer> mapRU = new HashMap<>();
                for (int dx = 1; x + dx <= n; dx++) {
                    for (int dy = 1; y - dy >= 0; dy++) {
                        int x1 = x;
                        int y1 = y - dy;
                        int x2 = x + dx - 1;
                        int y2 = y - 1;
                        int sum = getSum(pSum, y1, x1, y2, x2);
                        mapRU.put(sum, mapRU.getOrDefault(sum, 0) + 1);
                    }
                }

                //왼족 아래
                for (int dx = 1; x - dx >= 0; dx++) {
                    for (int dy = 1; y + dy <= n; dy++) {
                        int x1 = x - dx;
                        int y1 = y;
                        int x2 = x - 1;
                        int y2 = y + dy - 1;
                        int sum = getSum(pSum, y1, x1, y2, x2);
                        if (mapRU.containsKey(sum)) ans += mapRU.get(sum);
                    }
                }
            }
        }
        System.out.println(ans);
    }

    //(y1,x1)이 (y2,x2)보다 좌하단에 위치해야함
    static int getSum(int[][] psum, int y1, int x1, int y2, int x2) {
        return psum[y2][x2]
                - (y1 > 0 ? psum[y1 - 1][x2] : 0)
                - (x1 > 0 ? psum[y2][x1 - 1] : 0)
                + (y1 > 0 && x1 > 0 ? psum[y1 - 1][x1 - 1] : 0);
    }
}
