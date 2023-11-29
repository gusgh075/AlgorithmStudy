package DP;

class 도둑질 {
    public int solution(int[] money) {
        int[] dp=new int[money.length];
        int answer;

        //마지막 집을 고려 안하는 경우
        dp[0]=money[0];
        dp[1]= Math.max(money[1], money[0]);
        for(int now=2;now<dp.length-1;now++){
            dp[now]=Math.max(dp[now-1],dp[now-2]+money[now]);
        }
        answer=dp[dp.length-2];

        //처음 집을 고려 안하는 경우
        dp[0]=0;
        dp[1]=money[1];
        for(int now=2;now<dp.length;now++){
            dp[now]=Math.max(dp[now-1],dp[now-2]+money[now]);
        }
        answer=Math.max(answer,dp[dp.length-1]);

        return answer;
    }
}