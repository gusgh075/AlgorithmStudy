/*
팀정하기
친구의 친구 = 친구
원수의 원수 = 친구

친구 - 친구 = 같은 팀 = 모두 친구

인간관계가 주어질 때, 몇 팀이 만들어 질 수 있는가?

=> 원수의 원수 = 친구라는 공식을 잘 이해해야한다

친구는 union-find로 식별 가능
원수는?
=> 트리구조 사용해보자
ArraysList<Integer>[] 레쓰고

 */

import java.io.*;
import java.util.*;

public class G2_BJ1765_닭싸움팀정하기 {
    static int[] unf;

    public static int find(int v) {
        if (unf[v] == v) return v;
        else return unf[v] = find(unf[v]);
    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            if (fa > fb) unf[fa] = fb;
            else unf[fb] = fa;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        unf = new int[n + 1];
        ArrayList<Integer>[] enemy = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            unf[i] = i;
            enemy[i] = new ArrayList<>();
        }
        while (m-- > 0) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[1]);
            int b = Integer.parseInt(input[2]);
            if (input[0].equals("E")) {
                enemy[a].add(b);
                enemy[b].add(a);
            }
            if (input[0].equals("F")) {
                union(a, b);
            }
        }
//        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            for (Integer e : enemy[i]) {
                for (Integer f : enemy[e]) {
                    if (i == f) continue;
                    union(f,i);
                }
            }

        }
        HashSet<Integer> team = new HashSet<>();
        for (int i = 1; i < n + 1; i++) {
            team.add(find(i));
        }
        System.out.println(team.size());
//        System.out.println(Arrays.toString(unf));
    }
}
