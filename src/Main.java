//public class G4_BJ1967_트리의지름 {

import java.util.*;

public class Main {
    public static class Node {
        int data;
        List<Route> route;

        Node(int data) {
            this.data = data;
        }
    }

    public static class Route {
        Node destination;
        int distance;

        Route(Node destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer,Node> hashtable=new Hashtable<>();
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int sND = input[0]; // start Node Data
            int eND = input[1]; // end Node Data
            int dist = input[2]; // distance
            hashtable.put(sND,hashtable.getOrDefault(sND,new Node(sND))); //sND hashtable put
            hashtable.put(eND,hashtable.getOrDefault(eND,new Node(eND))); //eND hashtable put
            Route sRoute=new Route(hashtable.get(eND),dist); //
            Route eRoute=new Route(hashtable.get(sND),dist);
        }
    }

}
