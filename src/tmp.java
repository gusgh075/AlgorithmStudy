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
        Node lastNode = null; //말단노드 도착여부. 우측조회시 사용
        while (now != null || !s.isEmpty()) {
            while (now != null ) {
                s.push(now);
                now = now.left;
            }
            now = s.pop();
            //오른쪽 트리 조회중
            if (now.right != null && lastNode!=now.right) {
                s.push(now);
                now = now.right;
            }
            //오른쪽 트리 조회가 완료되었다면, 스택 출력중
            else{
                System.out.print(now.data);
                lastNode=now;
                now=null; //왼쪽트리는 이미 조회가 완료되었으므로, 조회하지 못하게 설정
            }
        }
    }

    public static void main(String[] args)throws IOException {
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
//        sPreOrder(map.get("A"));
//        System.out.println();
//        sInOrder(map.get("A"));
        sPostOrder(map.get("A"));
        System.out.println();
    }
}
