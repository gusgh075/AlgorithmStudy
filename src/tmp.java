import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;

public class tmp {
    public static class Node {
        String data;
        Node left;
        Node right;

        Node(String data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void sPostOrder(Node now) {
        Stack<Node> s = new Stack<>();
        Node lastRightNode = null;
        while (now != null || !s.isEmpty()) {
            while (now != null) {
                s.push(now);
                now = now.left;
            }
            now = s.pop();
            if (now.right != null && lastRightNode!=now.right) {
                s.push(now);
                now = now.right;
                lastRightNode = now.right;
            } else if (lastRightNode != now.right){
                System.out.print(now.data);
                now = now.right;
            }
        }
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<String,Node> map = new Hashtable<>();
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            if (!input[0].equals(".") && !map.containsKey(input[0])) {
                map.put(input[0], new Node(input[0]));
            }
            if (!input[1].equals(".") && !map.containsKey(input[1])) {
                map.put(input[1], new Node(input[1]));
            }
            if (!input[2].equals(".") && !map.containsKey(input[2])) {
                map.put(input[2], new Node(input[2]));
            }
            Node parent = map.get(input[0]);
            Node left = map.get(input[1]);
            Node right = map.get(input[2]);
            if (left == null) parent.left = right;
            else {
                parent.left = left;
                parent.right = right;
            }
        }
//        preOrder(map.get("A"));
//        System.out.println();
//        inOrder(map.get("A"));
//        System.out.println();
//        postOrder(map.get("A"));
//        sPreOrder(map.get("A"));
//        System.out.println();
//        sInOrder(map.get("A"));
        sPostOrder(map.get("A"));
    }
}
