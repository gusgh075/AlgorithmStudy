package DP;

/**
 * 문제 이해
 * - 각 집에 훔칠 수 있는 돈이 있다.
 * - 연속된 집을 털면 안된다.
 * - 집은 3개이상 1_000_000개이하이다.
 * - 돈은 0이상 1_000이하이다.
 * 풀이
 * 1. DP로 푼다. 모든 부분문제를 돌아다니고, 그 값을 저장하므로
 * 2. 그리디는 아니다. 모든 부분문제를 돌아다녀야한다.
 * 3. 처음과 마지막 집을 제외하고 최댓값을 구한다.
 * 4. 전전집 최댓값 + 현재 집 vs 전집 최댓값
 * 5. 1~마지막-1의 최댓값을 구했다면, 이제 처음집 고려 vs 마지막집 고려한 값
 * 6. ex) Math.max(f[1],f[2]+f[0]) or Math.max(f[end-1],f[end-2]+f[end])
 * 알아가야 할 점
 * 1. 부분문제를 풀 때 필요한 정보들을 정리 ex) n번째 집을 조사하려면, n-2와 n-1을 조사
 * 2. 처음집과 마지막집을 서로 제외한 상태에서 2번 조사하는 이유
 * 3. 일단, 처음집 조사 flag를 세워봤는데, 1, 2번째집의 돈이 같은 경우도 있어서 문제가 있었다.
 * 4. 직관적으로 보면, 처음집 조사하는 경우, 마지막집 조사하는 경우로 나누는게 나은것같다.
 */
class 도둑질 {
    public int solution(int[] money) {
        int answer = 0;
        int dp[] = new int[money.length];
        if (money.length == 3) {
            answer = Math.max(Math.max(money[0], money[1]), money[2]);
            return answer;
        }
        //마지막 집 제외
        dp[0] = money[0];
        dp[1] = Math.max(dp[0], money[1]);
        for (int i = 2; i < dp.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }
        answer = dp[dp.length - 2];
        //처음 집 제외
        dp[0] = 0;
        dp[1] = money[1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }
        answer = Math.max(answer, dp[dp.length - 1]);
        return answer;
    }

//    public int solution(int[] money) {
//        int[] dp=new int[money.length];
//        int answer;
//
//        //마지막 집을 고려 안하는 경우
//        dp[0]=money[0];
//        dp[1]= Math.max(money[1], money[0]);
//        for(int now=2;now<dp.length-1;now++){
//            dp[now]=Math.max(dp[now-1],dp[now-2]+money[now]);
//        }
//        answer=dp[dp.length-2];
//
//        //처음 집을 고려 안하는 경우
//        dp[0]=0;
//        dp[1]=money[1];
//        for(int now=2;now<dp.length;now++){
//            dp[now]=Math.max(dp[now-1],dp[now-2]+money[now]);
//        }
//        answer=Math.max(answer,dp[dp.length-1]);
//
//        return answer;
//    }
}