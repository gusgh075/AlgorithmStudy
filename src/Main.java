import java.util.*;
import java.io.*;

public class Main {
    public static class Node {
        Object data;
        Node left;
        Node right;

        Node(Object data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /*
    함수를 통한 전위,중위,후위 순회 구현
     */
    public static void preOrder(Node start) {
        System.out.print(start.data);
        if (start.left != null) preOrder(start.left);
        if (start.right != null) preOrder(start.right);
    }

    public static void inOrder(Node start) {
        if (start.left != null) inOrder(start.left);
        System.out.print(start.data);
        if (start.right != null) inOrder(start.right);
    }

    public static void postOrder(Node start) {
        if (start.left != null) postOrder(start.left);
        if (start.right != null) postOrder(start.right);
        System.out.print(start.data);
    }

    public static void sPreOrder(Node start) {
        Stack<Node> s = new Stack<>();
        s.add(start);
        while (!s.isEmpty()) {
            Node now = s.pop();
            System.out.print(now.data);
            if (now.right != null) s.add(now.right);
            if (now.left != null) s.add(now.left);
        }
    }

    public static void sInOrder(Node start) {
        Stack<Node> s = new Stack<>();
        s.add(start);
        Node cur=start;
        while (cur!=null ||!s.isEmpty()) {
            while(cur!=null){
                s.push(cur);
                cur=cur.left;
            }
            cur = s.pop();
            System.out.print(cur.data);

            cur=cur.right;
        }
    }

    public static void sPostOrder(Node start) {
        Stack<Node> s = new Stack<>();
        s.add(start);
        while (!s.isEmpty()) {
            Node now = s.pop();

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<String, Node> map = new Hashtable<>();
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
            parent.left = left;
            parent.right = right;
        }
//        preOrder(map.get("A"));
//        System.out.println();
//        inOrder(map.get("A"));
//        System.out.println();
//        postOrder(map.get("A"));
        sPreOrder(map.get("A"));
        System.out.println();
        sInOrder(map.get("A"));


    }
}