package BinarySearch;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S2_BJ2805_나무자르기 {
  /*
  상근이는 나무 M미터
  절단기 높이 H
  톱날이 땅으로부터 H미터 위로
  한줄에 연속해 있는 나무를 모두 절단, 횡으로
  나무 중 잘린 나무들만 들고 가는 것
  설정할 수 있는 높이는 양의 정수 또는 0
  => 나무를 필요한 만큼만 집으로 => M미터의 나무를 집으로 가져간다
  입력 : N,M
  범위 : (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)
  둘째줄에는 나무의 높이
  */
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    long n = input[0]; // 나무 수 n
    long m = input[1]; // 가져가려고 하는 나무의 길이 m
    long[] trees = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    Arrays.sort(trees);
    long start = 0;
    long end = trees[trees.length - 1];
    long answer = 0;
    while (start <= end) {
      long mid = (start + end) / 2;
      long sum = 0;
      for (long tree : trees) {
        sum += (tree - mid) <= 0 ? 0 : (tree - mid);
        if (sum >= m) break;
      }
      // 가져갈목재가 목표보다 많을 때
      if (sum >= m) {
        answer = mid;
        start = mid + 1;
      }
      // 가져갈 목재가 목표보다 적을 때
      else {
        end = mid - 1;
      }
    }
    System.out.println(answer);

  }
}
