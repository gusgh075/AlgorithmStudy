package DP;

/**
 * 조건
 * 1. 거쳐간 숫자의 합이 가장 큰 것을 return
 * 2. 이동방향은 대각선 좌측, 우측 하단
 * 전략
 * 1. 일단 각 자리마다 최댓값인 경우를 저장한다
 * 2. 이는 2차원 배열로 진행한다
 * 3. 각 자리마다 어떤 값이 가장 큰지 조사한다
 * 유의사항
 * - 높이는 500, 삼각형 최대수는 9,999이므로 오류발생하지 않음
 * - 재귀를 사용하면, 처리해야 할 변수가 늘어나고, 방문한 곳을 다시 방문하게 되며 코드가 늘어진다.
 */
class 정수삼각형 {

    public int solution(int[][] triangle) {
        int answer = 0;
        //재귀를 쓰지 않은 풀이방법
        //triangle을 DP로 설정
        for (int layer = 1; layer < triangle.length; layer++) {
            //첫 index
            triangle[layer][0] += triangle[layer - 1][0];
            //두번째부터 마지막전까지의 index
            for (int index = 1; index < triangle[layer].length - 1; index++) {
                triangle[layer][index] += Math.max(triangle[layer - 1][index - 1], triangle[layer - 1][index]);
            }
            //마지막 index
            triangle[layer][triangle[layer].length - 1] += triangle[layer - 1][triangle[layer - 1].length - 1];
        }
        //마지막 layer 중 최댓값 조사
        for (int i = 0; i < triangle[triangle.length - 1].length; i++) {
            answer = Math.max(triangle[triangle.length - 1][i], answer);
        }
        return answer;
    }

}