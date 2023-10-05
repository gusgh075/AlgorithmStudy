package BFS;

import java.util.Stack;

class 네트워크 {
    public int solution(int n, int[][] computers) {
        Stack<Integer> con = new Stack<>();
        boolean[] visit = new boolean[n];


        int answer = 0;
        int num=0;
        int pos;

        while(num<n) {
            for(int i=0;i<n;i++){
                if(!visit[i]) {
                    con.add(i);
                    visit[i]=true;
                    break;
                }
            }
            while(!con.isEmpty()) {
                pos = con.pop();
                num++;
                for (int i = 0; i < n; i++) {
                    if (computers[pos][i] == 1 && !visit[i]) {
                        visit[i] = true;
                        con.add(i);
                    }
                }
            }
            answer++;
        }


        return answer;
    }

}