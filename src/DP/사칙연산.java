package DP;

import java.util.Arrays;

class 사칙연산 {
    static public int solution(String arr[]) {
        int num = arr.length / 2+1;
        int[][] maxDP=new int[num+1][num+1];
        int[][] minDP=new int[num+1][num+1];
        for (int i = 0; i < maxDP.length; i++) {
            Arrays.fill(maxDP[i],Integer.MIN_VALUE);
        }
        for (int i = 0; i < minDP.length; i++) {
            Arrays.fill(minDP[i],Integer.MAX_VALUE);
        }
        for(int i=1;i<=num;i++){
            int cur = Integer.parseInt(arr[(i-1)*2]);
            maxDP[i][i]=cur;
            minDP[i][i]=cur;
        }
        //범위
        for(int step=1;step<num;step++){
            //시작점
            for(int start=1;start<=num-step;start++){
                int end=start+step;
                //중간점
                for(int mid=start;mid<end;mid++){
                    if(arr[(mid-1)*2+1].equals("+")){
                        maxDP[start][end]=Math.max(maxDP[start][mid]+maxDP[mid+1][end],maxDP[start][end]);
                        minDP[start][end]=Math.min(minDP[start][mid]+minDP[mid+1][end],minDP[start][end]);
                    }
                    else{
                        maxDP[start][end]=Math.max(maxDP[start][mid]-minDP[mid+1][end],maxDP[start][end]);
                        minDP[start][end]=Math.min(minDP[start][mid]-maxDP[mid+1][end],minDP[start][end]);
                    }
                }
            }
        }
        return maxDP[1][num];
    }
}