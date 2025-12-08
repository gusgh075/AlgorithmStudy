package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class S1_BJ11286_절댓값힙 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PriorityQueue<Integer> pq = new PriorityQueue<>(
        (o1, o2) ->
            (Math.abs(o1) - Math.abs(o2)) == 0
                ? o1 - o2
                : Math.abs(o1) - Math.abs(o2));
    int n = Integer.parseInt(br.readLine());
    for (int i = 0; i < n; i++) {
      int input = Integer.parseInt(br.readLine());
      if (input == 0) {
        Integer poll = pq.poll();
        System.out.println(poll == null ? 0 : poll);
      } else pq.add(input);
    }
  }
}
