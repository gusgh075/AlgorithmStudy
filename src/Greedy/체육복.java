package Greedy;

import java.util.Arrays;

/**
 * 전략
 * 1. 학생의 수는 2이상 30 이하
 * 2. 잃어버린 학생에게 여분이 있는가? -> reserve와 lost에서 제거
 * 3. 최적해는? reserve를 다 사용할 수 있도록 하는 것
 * 4. 그렇다면? 탐욕적 선택 속성은 lost의 앞번호!부터 reserve를 받도록 하는 것
 * 아쉬웠던 점
 * 1. 내가 생각했던 조건은 정렬되어있는 배열이었다.
 * 2. 허나, 배열들이 정렬되어있다는 보장은 없었다.
 * 3. 하여, Arrays.sort를 진행하니 바로 정답이었다.
 * 알아야될 점
 * 1. 이번 풀이는 자료수가 굉장히 적기때문에 단순하게 짰다.
 * 2. 허나, n이 100_000개 되는 순간부터는, 시간오류가 날것이다.
 * 3. 개선하는 방법을 연습해보자.
 */
class 체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        Arrays.sort(lost);
        Arrays.sort(reserve);
        // 개선완료
        int index = 0;
        for (int i = 0; i < lost.length; i++) {
            for (int j = index; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) {
                    //-3인 이유, -1이면 reserve[i]가 0일때, -1도 포함
                    lost[i] = -3;
                    reserve[j] = -3;
                    //추가
                    index = j+1;
                    break;
                }
            }
        }
        // 개선완료
        index = 0;
        for (int i = 0; i < reserve.length; i++) {
            for (int j = index; j < lost.length; j++) {
                if (reserve[i] - 1 == lost[j] || reserve[i] + 1 == lost[j]) {
                    reserve[i] = -3;
                    lost[j] = -3;
                    //추가
                    index = j+1;
                    break;
                }
            }
        }
        for (int i = 0; i < lost.length; i++) {
            if (lost[i] != -3)
                answer--;
        }
        return answer;
    }
}