package Greedy;
/*
그리디
1. 탐욕적 선택 속성
- 앞에서부터 큰 수를 마주하면 이전것을 다 버리고 넣는다.
2. 최적 부분 구조
- 각 수를 조회하는 상황을 부분문제라 볼 수 있음
풀이 개요(시간오류)
1. 제일 앞에서부터 큰 수를 찾는다
2. 그 수의 앞부분을 제거할 수 있는지 확인한다.
2-1. 가능할 시, 제거 후 다시 제일 큰수부터 조사한다.
2-2. 불가능할 시 그 다음 수로 1번으로 간다.
풀이 개선
1. 제일 큰수의 위치를 찾는다.
2. 큰수의 앞부분을 제거할 수 있는지 확인한다.
2-1. 가능할 시, 제거 후 큰수를 뒷부분에서 다시 조회한다.(->2.)
2-2. 불가능할 시, 큰수를 하나 내리고, 앞부분을 substring한 후 2번으로 간다.
3. k가 0이 되거나, 1까지 조회를 마치면 반복을 끝낸다.
4. k가 남았다면 가장 작은수를 삭제, 이후, 남은 수를 answer뒤에 더하고 반환한다.
정답을 보고 난 후
1. Stack 자료구조를 통해, 앞에서부터 조회
2-1. 들어오는 수가 Stack의 윗부분보다 크다면, 삭제한다.
2-2. k가 0일시, amswer에 현재 index이후의 숫자들을 전부 넣고 탈출.
2-3. stack에 현재 index의 수 추가.
3-1. k가 남아있다면, stack의 뒷부분부터 k만큼 제거.
3-2. answer에 stack을 전부 넣고 종료.
아쉬웠던 점
1. 앞에서부터 조사하지 못하고, 중간에서부터 찾아서 복잡해졌다.
2. 지워야할 숫자개수가 남아있을때를 고려하지 않았다.
유의해야 할 점
1. 찾는과정(조회)을 다방면에서 생각해보자
 */


import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Integer> stack = new Stack<>();
        //앞에서부터 조회
        for (int i = 0; i < number.length(); i++) {
            int num = Character.getNumericValue(number.charAt(i));
            //현재 수가 stack의 가장 위에있는 수보다 작다면,
            //지울수 있을때까지 지운다.
            while (k > 0) {
                if (!stack.isEmpty() && stack.peek() < num) {
                    k--;
                    stack.pop();
                } else break;
            }
            //지울수 있는 수가 없다면 탈출한다
            if (k == 0) {
                answer = answer + number.substring(i);
                break;
            }
            stack.add(num);
        }
        //지워야될 수가 남아있따면, 위에서부터 지운다(stack은 위에서부터 작은수)
        while (k-- > 0)
            stack.pop();
        while (!stack.isEmpty())
            answer = stack.pop() + answer;
        return answer;
    }
}
