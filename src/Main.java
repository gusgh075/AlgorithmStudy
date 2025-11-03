import java.util.*;
public class Main{
    public static class Node{
        Object data;
        Node left;
        Node right;
        Node(Object data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Node> nList = new ArrayList<>();
        int n=Integer.parseInt(sc.nextLine());
        for(int i=0;i<n;i++){
            String[] input=sc.nextLine().split(" ");
        }
    }
}