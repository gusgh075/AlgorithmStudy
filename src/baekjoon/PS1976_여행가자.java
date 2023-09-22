package baekjoon;

import java.io.*;
import java.util.Stack;

class PS1976_여행가자 {
    public void PS1976_여행가자() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int cityn = Integer.parseInt(br.readLine());
        int plann = Integer.parseInt(br.readLine());
        int[] plan = new int[plann];
        int[][] cityPath = new int[cityn + 1][cityn + 1];
//        입력
        for (int i = 1; i <= cityn; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 1; j <= cityn; j++) {
                cityPath[i][j] = Integer.parseInt(s[j - 1]);
            }
        }
        {
            String[] s = br.readLine().split(" ");
            for (int i = 0; i < plann; i++) {
                plan[i] = Integer.parseInt(s[i]);
            }
        }

        /**
         * 1. 첫번째 방문도시가 갈수있는 도시들을 탐색
         * 2. 계획에 갈수없는 도시가 있을시 return NO
         */
        Stack<Integer> save = new Stack<>();
        boolean[] visit = new boolean[cityn + 1];
        save.add(plan[0]);
        visit[plan[0]] = true;

        while (!save.isEmpty()) {
            int cur = save.pop();
            for (int i = 1; i <= cityn; i++) {
                if (visit[i])
                    continue;
                if (cityPath[cur][i] == 1) {
                    save.add(i);
                    visit[i] = true;
                }
            }
        }

        for (int cur : plan) {
            if (!visit[cur]) {
                bw.write("NO");
                bw.flush();
                bw.close();
                br.close();
                return;
            }
        }
        bw.write("YES");
        bw.flush();
        bw.close();
        br.close();
    }
}
