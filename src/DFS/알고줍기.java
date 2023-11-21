package DFS;

class 알고줍기 {
    boolean[][] filled = new boolean[52][52];
    boolean[][] point = new boolean[52][52];
    boolean[][] visited = new boolean[52][52];
    boolean[][][] line = new boolean[52][52][4];
    int[] mvX = {1, 0, -1, 0};
    int[] mvY = {0, -1, 0, 1};

    int itemX;
    int itemY;
    int result = Integer.MAX_VALUE;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        this.itemX = itemX;
        this.itemY = itemY;
        for (int[] ints : rectangle) {
            for (int x = ints[0]; x < ints[2]; x++) {
                for (int y = ints[1]; y < ints[3]; y++) {
                    filled[x][y] = true;
                }
            }
        }
        for (int x = 1; x < 50; x++) {
            for (int y = 1; y < 50; y++) {
                if (filled[x][y]) {
                    if (!filled[x + 1][y]) {
                        line[x + 1][y][3] = true;
                        line[x + 1][y + 1][1] = true;
                        point[x + 1][y] = true;
                        point[x + 1][y + 1] = true;
                    }
                    if (!filled[x][y + 1]) {
                        line[x][y + 1][0] = true;
                        line[x + 1][y + 1][2] = true;
                        point[x][y + 1] = true;
                        point[x + 1][y + 1] = true;
                    }
                    if (!filled[x - 1][y]) {
                        line[x][y][3] = true;
                        line[x][y + 1][1] = true;
                        point[x][y] = true;
                        point[x][y + 1] = true;
                    }
                    if (!filled[x][y - 1]) {
                        line[x][y][0] = true;
                        line[x + 1][y][2] = true;
                        point[x][y] = true;
                        point[x + 1][y] = true;
                    }
                }
            }
        }
        DFS(characterX, characterY, 0);
        int answer = result;
        return answer;
    }

    public void DFS(int characterX, int characterY, int cnt) {
        if (cnt > result)
            return;
        if (characterX == itemX && characterY == itemY) {
            result = Math.min(result, cnt);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextX = characterX + mvX[i];
            int nextY = characterY + mvY[i];
            if (point[nextX][nextY] && !visited[nextX][nextY]
                    &&line[characterX][characterY][i]) {
                visited[nextX][nextY] = true;
                DFS(nextX, nextY, cnt + 1);
                visited[nextX][nextY] = false;
            }
        }
    }
}