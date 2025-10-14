package BFS;

import java.util.*;
//1327_소트게임 문제와 유사
public class G2_BJ1525_퍼즐 {
    static int[] dxy = {1, 3, -1, -3};

    public static class Pos {
        int[] map;
        int cnt;
        int zero;

        public Pos(int[] map, int cnt, int zero) {
            this.map = map.clone();
            this.cnt = cnt;
            this.zero = zero;
        }
    }

    public static boolean check(int[] map) {
        for (int i = 0; i < 8; i++) {
            if (map[i] != (i + 1))
                return false;
        }
        return true;
    }

    public static int BFS(int[] map, int zero) {
        Queue<Pos> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.add(new Pos(map, 0, zero));
        visited.add(Arrays.toString(map));
        while (!q.isEmpty()) {
            Pos p = q.poll();
            if (check(p.map)) return p.cnt;
            for (int d = 0; d < 4; d++) {
                int nz = p.zero + dxy[d];
                if (!(Math.abs((p.zero % 3) - (nz % 3)) == 1    //같은 횡에 있거나
                        || (p.zero % 3) == (nz % 3))) {     //같은 열에 있거나
                    continue;
                }
                if (nz < 0 || nz >= 9) continue;
                int[] clone = p.map.clone();
                clone[p.zero] = p.map[nz];
                clone[nz] = p.map[p.zero];
                String str = Arrays.toString(clone);
                if (visited.contains(str)) continue;
                visited.add(str);
                q.add(new Pos(clone, p.cnt + 1, nz));
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] map = new int[9];
        int zero = 0;
        for (int i = 0; i < 9; i++) {
            map[i] = sc.nextInt();
            if (map[i] == 0) zero = i;
        }
        System.out.println(BFS(map, zero));
    }
}
