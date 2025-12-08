package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class S2_BJ1927_최소힙 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    PriorityQueue<Long> pq = new PriorityQueue<>();
    for (int i = 0; i < n; i++) {
      Long input = Long.parseLong(br.readLine());
      if(input )
      pq.offer(input);
    }
  }
}
