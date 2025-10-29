import java.io.*;
import java.util.Arrays;

/*
만약 2-3-4-1 이렇게 연결되어있을 때, unf[3]에는 4가 들어가야 root에 1이 들어갈 수 있음.
 */
public class Main {
    static int[] unf;//루트를 저장
    static int[] road;//해당 집합의 루트에 길의 개수를 저장시켜놓음
    static boolean[][] visited;//visited[i][j] => i와 j를 연결하는 길을 방문했는지

    /**
     * 루트 찾는 함수
     *
     * @param v
     * @return
     */
    public static int find(int v) {
        if (unf[v] == v) return v;
        else return unf[v] = find(unf[v]);
    }

    /**
     * @param a
     * @param b
     */
    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            if(fa>fb){
                unf[fa] = fb;
            }
            else {
                unf[fb] = fa;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        unf = new int[n];
        for (int i = 0; i < n; i++) {
            unf[i]=i;
        }
        road = new int[n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                char c = input.charAt(j);
                if (c == 'Y') {
                    //방문하지 않은 길이라면,
                    if (!visited[i][j]) {
                        if (i > j) road[j]++;
                        else road[i]++;
                        visited[i][j] = true;
                        visited[j][i] = true;
                        union(i,j);
                    }
                }
            }
        }
        System.out.println(Arrays.toString(road));
        System.out.println(Arrays.toString(unf));
    }
}
