package DP;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. N인 숫자를 여러개 사용한다
 * 2. 사칙연산 혹은 숫자 붙이기를 한다
 * 3. N 사용횟수의 최솟값을 찾는다
 * 4. 사용횟수는 최대 8이다.
 * 전략
 * - 각 경우의 수들을 저장한다
 * - DP의 Bottom-up 전략을 사용한다
 * - N이 1번쓰일때부터 여러번 쓰이는 경우들을 다 저장할 것이다.
 * - 중복을 없애기 위해, DP자료형은 Set을 사용한다
 * 느낀점
 * - 모든 경우의 수를 고려하라
 * - size를 2부터 설정해서, 정답이 1일때의 경우를 생각하지 못해 틀렸었다.
 */
class N으로표현 {
    public int solution(int N, int number) {
        Set[] DP = new Set[9];
        for (int i = 1; i < DP.length; i++) {
            DP[i] = new HashSet<Integer>();
            DP[i].add(Integer.parseInt(Integer.toString(N).repeat(i)));
        }
        for (int size = 1; size < 9; size++) {
            for (int front = 1; front < size; front++) {
                int back = size - front;
                for (Object f : DP[front]) {
                    for (Object b : DP[back]) {
                        int f1 = (int) f;
                        int b1 = (int) b;
                        DP[size].add(f1 + b1);
                        DP[size].add(f1 - b1);
                        DP[size].add(f1 * b1);
                        if (b1 != 0)
                            DP[size].add(f1 / b1);
                    }
                }
            }
            if (DP[size].contains(number)) {
                return size;
            }
        }
        return -1;
    }
}