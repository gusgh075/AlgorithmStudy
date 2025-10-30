import java.io.*;
import java.util.*;

/*
도로를 수정할때 무조건 둘 중 하나에 붙어있어야 되는구나!!!!
 */
public class G1_BJ1119_그래프 {
    static int[] unf;
    static int[] road;

    public static int find(int v) {
        if (v == unf[v]) return v;
        return unf[v] = find(unf[v]);
    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) { // 이미 연결된 경우 방지
            if (fa < fb) {
                unf[fb] = fa;
                road[fa] += road[fb];
                road[fb] = 0;
            } else {
                unf[fa] = fb;
                road[fb] += road[fa];
                road[fa] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(0);
            return;
        }
        unf = new int[n];
        road = new int[n];
        int roadSum = 0;
        for (int i = 0; i < n; i++) unf[i] = i;

        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            String noneRoadCityChk = line.replace("N", "");
            if (noneRoadCityChk.isEmpty()) {
                System.out.println(-1);
                return;
            }
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == 'Y' && i < j) { // 중복 방지
                    road[i]++;  //낮은 숫자의 도시에 road ++;
                    roadSum++;
                    union(i, j);
                }
            }
        }
<<<<<<< HEAD
=======
        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int fi = find(unf[i]);
            roots.add(fi);
            if (fi == i) continue;
            road[fi] += road[i];
            road[i] = 0;
        }

        int k = roots.size(); // 연결 요소의 개수
        if (roadSum < n - 1) {
            System.out.println(-1); // 도로 부족
        } else {
            // roadSum >= n - 1: 도로 충분, k-1번의 연결이 필요
            System.out.println(k - 1);
        }

//        System.out.println(Arrays.toString(road));
//        System.out.println(Arrays.toString(unf));
>>>>>>> 백준-그래프
    }
}
