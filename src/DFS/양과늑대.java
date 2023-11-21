package DFS;

import java.util.ArrayList;

class 양과늑대 {
    int[] info;
    int answer = 0;
    ArrayList<Integer>[] children;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        children=new ArrayList[info.length];
        for (int[] edge : edges) {
            if(children[edge[0]]==null)
                children[edge[0]]=new ArrayList<>();
            children[edge[0]].add(edge[1]);
        }

        ArrayList<Integer> integers = new ArrayList<>(0);

        DFS(0,0,0,integers);
        return answer;
    }

    public void DFS(int pos, int sheep, int wolf, ArrayList<Integer> nextPos) {
        if (info[pos] == 0) sheep++;
        else wolf++;
        if(wolf>=sheep)return;
        answer=Math.max(sheep,answer);
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(nextPos);
        list.remove((Integer) pos);
        if(children[pos]!=null) {
            list.addAll(children[pos]);
        }
        for (Integer i : list) {
            DFS(i, sheep, wolf, list);
        }
    }
}
