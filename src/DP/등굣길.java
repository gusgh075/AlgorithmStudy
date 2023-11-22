package DP;

class 등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[m + 1][n + 1];
        boolean[][] puddle = new boolean[m + 1][n + 1];
        int[] mvX = {-1, 0};
        int[] mvY = {0, -1};
        map[0][1] = 1;
        for (int[] ints : puddles) {
            puddle[ints[0]][ints[1]] = true;
        }
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (puddle[x][y])
                    continue;
                for (int i = 0; i < 2; i++) {
                    map[x][y]+=map[x+mvX[i]][y+mvY[i]];
                }
                map[x][y]%=1_000_000_007;
            }
        }
        int answer = map[m][n];
        return answer;
    }
}