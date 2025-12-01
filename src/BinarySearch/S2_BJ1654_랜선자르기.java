package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/* 랜선 자르기
 * - K개의 랜선 재고
 *     K개의 랜선, 길이는 제각각
 * - N개의 같은 길이의 랜선이 필요
 * - ex) 300cm 랜선 -> 140cm*2랜선 + 20cm
 * - N개보다 많이 만들어도 됨
 * => 만들 수 있는 최대 랜선의 길이는?
 *     N개 이상을 만들되, 그때 최대 길이를 가져가야함
 * 조건
 *   K<=10,000
 *   N<=1,000,000
 * */

/* 풀이
 * */
public class S2_BJ1654_랜선자르기 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int k = input[0];
    int n = input[1];
    Integer[] lan = new Integer[k];
    for (int i = 0; i < k; i++) {
      lan[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(lan, Comparator.reverseOrder());
    long start = 1;
    long end = lan[0];
    long answer = 0;
    /* 주어진 K개의 랜선의 최댓값과 0의 범위에서 n개를 구할 수 있는 최대 길이를 구해야 한다.*/
    /* 1. 임의의 랜선 길이를 정한다
     * 2. 해당 길이일 때, 몇 개의 랜선을 구할 수 있는지 정한다.
     * 2-1. 개수가 n보다 같거나 크다면, (현재길이 + 최대길이) / 2 를 하여 해당 값으로 정한 후에 2로 간다.
     * 2-2. 개수가 n보다 적다면, (0 + 현재길이) /2 를 하여 해당 값으로 정한 후에 2로 간다.
     * 3. 만일, 현재길이 = 최대길이라면 개수는 n개이며 길이는 최대길이인 것이므로 반환한다.
     *
     * 주의점 : 시작은 0이 아닌 1부터
              *answer 변수를 따로 설정해서, 마지막 값이 정답이라는 불확실성을 제거
              * while(start<=end)로 설정함으로써, start>end일때 탐색을 종료한다.
              * 이때, answer는 조건을 만족하는 최대길이를 담고 있음이 보장됨.
              * => start는 조건을 만족하지 않는 수 중 최솟값
              * => end는 조건을 만족하는 수 중 최댓값
              *
     * */
    while (start <= end) {
      int sum = 0;
      long now = (start+end)/2;
      for (Integer i : lan) {
        sum += i / now;
        if (sum >= n) break;
      }
      if (sum >= n) {
        answer = now;
        start = now + 1;
      } else {
        end = now - 1;
      }
    }
    System.out.println(answer);
  }
}
