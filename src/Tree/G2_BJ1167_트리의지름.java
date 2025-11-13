package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G2_BJ1167_트리의지름 {
    static Hashtable<Integer,Node> hashtable;
    static int N;
    public static class Node{
        int data;
        List<Route> routes;

        public Node(int data) {
            this.data = data;
            this.routes = routes;
        }
        public void addRoute(Route route){
            if(routes ==null) routes =new ArrayList<>();
            routes.add(route);
        }
    }
    public static class Route{
        Node end;
        int dist;
        public Route(Node end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }
    public static Node searchEndNode(Node start ){
        Queue<Node> q=new ArrayDeque<>();
        q.add(start);
        long[] distDP= new long[N+1];
        boolean[] visited = new boolean[N+1];
        Node now=null;
        while(!q.isEmpty()){
            now = q.poll();
            visited[now.data]=true;
            for (Route route : now.routes) {
                if(visited[route.end.data]) continue;
                Node end = route.end;
                int dist = route.dist;
                long distSum = distDP[now.data] + dist;
                if(distDP[end.data]<distSum ){
                    distDP[end.data]=distSum;
                    q.add(end);
                }
            }
        }
        Node endNode=null;
        long max=0;
        for (int i = 1; i < distDP.length; i++) {
            if(distDP[i]>max){
                max=distDP[i];
                endNode=hashtable.get(i);
            }
        }
        return endNode;
    }

    public static long searchMaxDist(Node start){
        Queue<Node> q=new ArrayDeque<>();
        q.add(start);
        long[] distDP= new long[N+1];
        boolean[] visited = new boolean[N+1];
        Node now=null;
        while(!q.isEmpty()){
            now = q.poll();
            visited[now.data]=true;
            for (Route route : now.routes) {
                if(visited[route.end.data]) continue;
                Node end = route.end;
                int dist = route.dist;
                long distSum = distDP[now.data] + dist;
                if(distDP[end.data]<distSum ){
                    distDP[end.data]=distSum;
                    q.add(end);
                }
            }
        }
        Node endNode=null;
        long max=0;
        for (int i = 1; i < distDP.length; i++) {
            if(distDP[i]>max){
                max=distDP[i];
                endNode=hashtable.get(i);
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hashtable=new Hashtable<>();
        for (int i = 0; i < N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            hashtable.put(input[0],hashtable.getOrDefault(input[0],new Node(input[0]))); //input[0] 노드를 hashtable에 추가
            for (int j = 1; j < input.length; j+=2) {
                if(input[j]==-1) break; //-1이면 해당 노드 경로 입력 종료
                Node end = hashtable.getOrDefault(input[j],new Node(input[j])); //경로 종착지
                hashtable.put(input[j],end);    //hashtable에 도착지 노드 저장
                int dist = input[j + 1];    //경로 거리
                hashtable.get(input[0]).addRoute(new Route(end,dist));  //해당 node에 route값 추가
                //반대 노드에도 경로 추가
                hashtable.get(input[j]).addRoute(
                        new Route(hashtable.get(input[0]),dist));
            }
        }
        Node start = null;
        for (Integer i : hashtable.keySet()) {
            start=hashtable.get(i);
            break;
        }
        start = searchEndNode(start);
        long l = searchMaxDist(start);
        System.out.println(l);
    }
}
