package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2110_공유기설치 {
  /* 집 N개 수직선 위에 있다.
   * X1, ..., Xn
   * n개의 집에 공유기 C개 설치
   * 가장 인접한 두 공유기 사이의 거리를 최대
   *
   * 입력 : N 집개수, C 공유기 개수
   * */
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    long n = input[0];
    long c = input[1];
    long[] machine = new long[(int) c];
    long[] house = new long[(int) n];
    for (int i = 0; i < n; i++) house[i] = Long.parseLong(br.readLine());

    // 200,000 c C
  }
}
