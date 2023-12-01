package DP;

import java.util.Arrays;

/**
 * 풀이
 * 1. dp를 사용
 * 2. 각 항을 계산할때, a-b, a+b인 경우가 있음
 * 3. a-b인 경우, a는 최대, b는 최소값이여야함
 * 4. a+b인 경우는, a와 b 둘다 최댓값이면 됨
 * 5. 그러므로 a-b인 경우를 위해, 최댓값인 경우와 최솟값인 경우를 둘다 저장
 * 고무적인점
 * 1. 에러가 뜨지 않음
 * 아쉬운 점
 * 1. 다시푸는거지만 1트 실패
 * 2. 제일 아쉬운점은 연산자의 위치를 start*2+1로 해서, 빙빙돌았다. mid*2+1로 했어야됐는데 말이다.
 */
class 사칙연산 {
    static public int solution(String arr[]) {
        int answer = 0;
        int[][] minDP = new int[arr.length / 2 + 1][arr.length / 2 + 1];
        int[][] maxDP = new int[arr.length / 2 + 1][arr.length / 2 + 1];
        for (int i = 0; i < minDP.length; i++) {
            Arrays.fill(minDP[i], Integer.MAX_VALUE);
            Arrays.fill(maxDP[i], Integer.MIN_VALUE);
        }
        for (int i = 0; i < minDP.length; i++) {
            minDP[i][i] = Integer.parseInt(arr[i * 2]);
            maxDP[i][i] = minDP[i][i];
        }
        //범위
        for (int step = 1; step < minDP.length; step++) {
            //시작점
            for (int start = 0; start < minDP.length - step; start++) {
                int end = start + step;
                //중간점
                for (int mid = start; mid < end; mid++) {
                    //여기서 arr[start*2+1]로 코딩해서, 시간버림
                    if (arr[mid * 2 + 1].equals("+")) {
                        maxDP[start][end] = Math.max(maxDP[start][mid] + maxDP[mid + 1][end], maxDP[start][end]);
                        minDP[start][end] = Math.min(minDP[start][mid] + minDP[mid + 1][end], minDP[start][end]);
                    }
                    if (arr[mid * 2 + 1].equals("-")) {
                        maxDP[start][end] = Math.max(maxDP[start][end], maxDP[start][mid] - minDP[mid + 1][end]);
                        minDP[start][end] = Math.min(minDP[start][end], minDP[start][mid] - maxDP[mid + 1][end]);
                    }
                }
            }
        }
        answer = maxDP[0][minDP.length - 1];
        return answer;
    }
}


//    static public int solution(String arr[]) {
//        int num = arr.length / 2+1;
//        int[][] maxDP=new int[num+1][num+1];
//        int[][] minDP=new int[num+1][num+1];
//        for (int i = 0; i < maxDP.length; i++) {
//            Arrays.fill(maxDP[i],Integer.MIN_VALUE);
//        }
//        for (int i = 0; i < minDP.length; i++) {
//            Arrays.fill(minDP[i],Integer.MAX_VALUE);
//        }
//        for(int i=1;i<=num;i++){
//            int cur = Integer.parseInt(arr[(i-1)*2]);
//            maxDP[i][i]=cur;
//            minDP[i][i]=cur;
//        }
//        //범위
//        for(int step=1;step<num;step++){
//            //시작점
//            for(int start=1;start<=num-step;start++){
//                int end=start+step;
//                //중간점
//                for(int mid=start;mid<end;mid++){
//                    if(arr[(mid-1)*2+1].equals("+")){
//                        maxDP[start][end]=Math.max(maxDP[start][mid]+maxDP[mid+1][end],maxDP[start][end]);
//                        minDP[start][end]=Math.min(minDP[start][mid]+minDP[mid+1][end],minDP[start][end]);
//                    }
//                    else{
//                        maxDP[start][end]=Math.max(maxDP[start][mid]-minDP[mid+1][end],maxDP[start][end]);
//                        minDP[start][end]=Math.min(minDP[start][mid]-maxDP[mid+1][end],minDP[start][end]);
//                    }
//                }
//            }
//        }
//        return maxDP[1][num];
//    }
