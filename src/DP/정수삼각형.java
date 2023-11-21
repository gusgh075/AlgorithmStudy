package DP;

class 정수삼각형 {
    public int solution(int[][] triangle) {
        int[][] dp=new int[triangle.length][];
        for(int i=0;i< triangle.length;i++){
            dp[i]=new int[triangle[i].length];
        }
        dp[0][0]=triangle[0][0];
        for(int i=0;i< triangle.length-1;i++){
            for(int j=0;j<triangle[i].length;j++){
                dp[i+1][j]=Math.max(dp[i][j]+triangle[i+1][j],dp[i+1][j]);
                dp[i+1][j+1]=Math.max(dp[i][j]+triangle[i+1][j+1],dp[i+1][j+1]);
            }
        }

        int answer = 0;
        for (int i : dp[triangle.length - 1]) {
            answer=Math.max(answer,i);
        }
        return answer;
    }
}