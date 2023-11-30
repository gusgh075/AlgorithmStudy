package DP;

/**
 * 풀이 순서
 * 1. 완전탐색을 통해 모든 요소를 방문한다
 * 2. 방문후, 상단과 좌측의 경로개수를 더한다.
 * (왜냐하면, 방문한 좌표로 갈수 있는 최단경로의 경우는, 상단과 좌측 경로 개수의 합이기 때문이다.)
 * 3. 학교의 경로개수가 어떻게 되는지 조사한다
 * 유의 사항
 * 1. 우측과 하단으로 이동하기만 하면 최단거리이다.
 * 2. 물웅덩이를 방문하면 조사하지 않는다.
 * 자료 구조
 * - dp 2차원 배열을 사용
 */

class 등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m + 1][n + 1];
        int[] nextX = {-1, 0};
        int[] nextY = {0, -1};
        dp[1][1] = 1;
        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];
            dp[x][y] = -1;
        }
        for (int x = 1; x <= m; x++) {
            for (int y = 1; y <= n; y++) {
                for (int i = 0; i < 2; i++) {
                    //물웅덩이를 조회하면 그냥 패스
                    if(dp[x][y]==-1)
                        continue;
                    // dp에 -1이면 더하지 않고 패스
                    if (dp[x + nextX[i]][y + nextY[i]] != -1) {
                        dp[x][y] += dp[x + nextX[i]][y + nextY[i]];
                        dp[x][y]%=1000000007;
                    }
                }
            }
        }
        int answer = dp[m][n];
        return answer;
    }
}