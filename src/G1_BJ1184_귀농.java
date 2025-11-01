import java.io.*;
import java.util.*;

/*
land[][] 중 꼭짓점 하나가 겹치는 정사각형 2개를 고른다
그리고 이 정사각형 2개 내부의 합은 같아야 한다
크기는 달라도 된다
이때 경우의 수는?
 */
public class G1_BJ1184_귀농 {
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};
    static int n;
    static int[][] land;
    static int[][][][] dp;      //dp[y][x][n][d]
    public static void DP(int x, int y){
        for (int i = 1; i <= n; i++) {

        }
    }
    public static int Sum(int x, int y, int n, int d){
        if(dp[y][x][n-1][d]==Integer.MAX_VALUE){
            int sum=0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                }
            }
            return sum;
        }
        else{
            int sum = dp[y][x][n - 1][d]+land[y+n*dy[d]][x+n*dx[d]];
            for (int i = 1; i < n; i++) {
                sum+=land[y+n*dy[d]][x+i*dx[d]];
                sum+=land[y+i*dy[d]][x+n*dx[d]];
            }
            return sum;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        land = new int[n][n];
        dp = new int[n][n][n][4];
        Arrays.fill(dp,Integer.MAX_VALUE);
        int[][][][] dp = new int[n][n][n][2];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                land[i][j] = Integer.parseInt(input[j]);
            }
        }
    }
}
