import java.io.*;
import java.util.*;

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
                road[fb]=0;
            } else {
                unf[fa] = fb;
                road[fb] += road[fa];
                road[fa]=0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        unf = new int[n];
        road = new int[n];
        int roadSum=0;
        HashSet<Integer> root=new HashSet<>();
        for (int i = 0; i < n; i++) unf[i] = i;

        for (int i = 0; i < n; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == 'Y' && i < j) { // 중복 방지
                    road[i]++;  //낮은 숫자의 도시에 road ++;
                    roadSum++;
                    union(i, j);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            int fi = find(unf[i]);
            if(fi==i)continue;
            road[fi]+=road[i];
            road[i]=0;
        }
        System.out.println(Arrays.toString(road));
        System.out.println(Arrays.toString(unf));
    }
}
