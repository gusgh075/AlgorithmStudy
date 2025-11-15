package Tree;

import java.util.*;

public class G4_BJ1967_트리의지름 {

    //public class Main {
    static Map<Integer, Node> hashtable;
    static long ansDist;
    static int n;

    public static class Node {
        int data;
        List<Route> route;

        Node(int data) {
            this.data = data;
            route = new ArrayList<>();
        }
    }

    public static class Route {
        Node dest;
        int distance;

        Route(Node dest, int distance) {
            this.dest = dest;
            this.distance = distance;
        }
    }

    public static Node searchEndNode(Node start) {
        if (start == null) return null;
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);
        boolean[] visited = new boolean[n + 1];
        long[] dp = new long[n + 1];
        while (!q.isEmpty()) {
            Node now = q.poll();
            if (visited[now.data]) continue;
            visited[now.data] = true;
            for (Route route : now.route) {
                Node nxtNode = route.dest;
                if (visited[nxtNode.data]) continue;
                dp[nxtNode.data] = dp[now.data] + route.distance;
                ansDist = Math.max(ansDist, dp[nxtNode.data]);
                q.add(nxtNode);
            }
        }
        Node endNode = null;
        for (int i = 1; i < n + 1; i++) {
            if (dp[i] == ansDist) {
                endNode = hashtable.get(i);
                break;
            }
        }
        return endNode;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        hashtable = new Hashtable<>();
        n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n - 1; i++) {
            int[] input = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int sND = input[0]; // start Node Data
            int eND = input[1]; // end Node Data
            int dist = input[2]; // distance
            hashtable.put(sND, hashtable.getOrDefault(sND, new Node(sND))); //sND hashtable put
            hashtable.put(eND, hashtable.getOrDefault(eND, new Node(eND))); //eND hashtable put
            hashtable.get(sND).route.add(new Route(hashtable.get(eND), dist)); // 노드에 경로 추가
            hashtable.get(eND).route.add(new Route(hashtable.get(sND), dist));
        }
        searchEndNode(
                searchEndNode(hashtable.get(1)));
        System.out.println(ansDist);
    }
}
