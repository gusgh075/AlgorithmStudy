package BFS;

import java.io.*;
import java.util.*;

/*
-조건
A집합과 B집합의 정점은 속한 집합의 다른 정점과 연결되어선 안된다
테스트 케이스 : 2 ≤ K ≤ 5
정점 : 1 ≤ V ≤ 20,000
간선 : 1 ≤ E ≤ 200,000
시간제한 2초 => 2억번 연산정도
-유의사항
간선은 정점의 순서대로 입력받지 않는다
입력값의 범위를 보니 시간복잡도를 신경써야됨
!양방향 그래프 => 집합[시작].add(끝); 집합[끝].add(시작);
-접근방법
a. 먼저 이분 그래프가 되려면, 간선에서 하나의 정점은 A, 다른 정점은 B에 속해야함
a-1. 입력받는대로 visited 배열을 통해서 A,B를 결정한 정점인지 확인함
a-2. 만일 visited가 true인 정점과 연결되어 있지 않은 간선이라면 pass함
a-3. 이때의, 최악 시간복잡도는 200,000!
b. 모든 간선을 List에 입력받고 두 정점중 낮은 정점 순으로 정렬하기
b-1. 이때의 시간복잡도는 O(nlogn) => 200,000 log 200,000 => 대략?80,000,000
b-2. 이후 List를 처음부터 조회하며 A와 B를 정함 => 이러면 pass하지 않고 전체 조회 가능
c. 양방향그래프로 정점 1부터 시작해서 해당 정점이 속한 그래프를 물들임
-인사이트
a. 편하게 돌면서 조회하기
b. 정렬해서 조회하기 => 생각해보니 연결되어있지 않은 간선이 있을 수 있음. => 그부분은 pass하면 a보다 훨씬 시간단축되지 않을까?
c. BF + BFS => 시작부터 양방향그래프로 모든 점점 조회(주어지지않은 정점도)
-입력
2
3 2
1 3
2 3
4 4
1 2
2 3
3 4
4 2
-출력
YES
NO
 */
public class G4_1707_이분그래프 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        for (int k = 0; k < t; k++) {
            String ans = "";
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());//정점의 개수
            int e = Integer.parseInt(st.nextToken());//간선의 개수
            List<Integer>[] graph = new ArrayList[v + 1];
            for (int i = 1; i <= v; i++) graph[i] = new ArrayList<Integer>();
            for (int i = 0; i < e; i++) {
                int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                graph[input[0]].add(input[1]);
                graph[input[1]].add(input[0]);
            }
            int[] color = new int[v + 1]; //1,-1 각 그래프를 의미 0 not visited
            for (int i = 1; i <= v; i++) {
                if (color[i] != 0) continue;
                Queue<Integer> q = new ArrayDeque<>();
                q.add(i);
                color[i] = 1;
                //여기서 해당 i정점이 속한 그래프의 모든 정점의 색을 정한다. 그래서 윗줄에 color[i]=1은 그래프의 초깃값이라 생각해도 된다.
                while (!q.isEmpty()) {
                    Integer now = q.poll();
                    for (Integer next : graph[now]) {
                        if (color[next] == 0) {
                            color[next] = -color[now];
                            q.add(next);    //색을 정한 정점이 연결된 간선들을 통해, 다음 정점의 색들을 모두 정함
                        }
                        if (color[next] == color[now]) {
                            ans = "NO\n";
                            q.clear();
                            break;
                        }
                    }
                }
            }
            if (ans.isEmpty()) ans = "YES\n";
            bw.write(ans);
        }
        bw.flush();
    }
}
