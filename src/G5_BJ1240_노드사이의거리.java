import java.util.*;

//public class Main {
public class G5_BJ1240_노드사이의거리{
    public static class Node {
        int data;
        List<Route> route;

        Node(int data) {
            this.data = data;
            route = new ArrayList<>();
        }
    }

    public static class Route {
        int dist;
        Node end;

        Route(Node end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] input = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int m = input[1];
        Map<Integer, Node> hashTable = new Hashtable<>();
        for (int i = 0; i < n - 1; i++) {
            input = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int sNodeData = input[0];
            int eNodeData = input[1];
            int dist = input[2];
            Node sNode = hashTable.getOrDefault(sNodeData, new Node(sNodeData));
            Node eNode = hashTable.getOrDefault(eNodeData, new Node(eNodeData));
            Route sRoute = new Route(eNode, dist);
            Route eRoute = new Route(sNode, dist);
            hashTable.put(sNodeData, sNode);
            hashTable.put(eNodeData, eNode);
            sNode.route.add(sRoute);
            eNode.route.add(eRoute);
        }
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Queue<Node> q = new ArrayDeque<>();
            q.add(hashTable.get(input[0]));
            int[] dp = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            while (!q.isEmpty()) {
                Node now = q.poll();
                if (visited[now.data]) continue;
                visited[now.data] = true;
                for (Route route : now.route) {
                    Node nxt = route.end;
                    int dist = route.dist;
                    if (visited[nxt.data]) continue;
                    dp[nxt.data] = dp[now.data] + dist;
                    q.add(nxt);
                    if (nxt.data == input[1]) {
                        System.out.println(dp[nxt.data]);
                        q.clear();
                        break;
                    }
                }
            }
        }
    }
}