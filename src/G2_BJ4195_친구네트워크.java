import java.io.*;
import java.util.*;

public class G2_BJ4195_친구네트워크 {
    public static int[] unf;
    public static int[] count;
    /*
    root를 찾음
    */
    public static int Find(int v){
        if(v==unf[v]) return v;
        else return unf[v]=Find(unf[v]);
    }
    public static void Union(int a, int b){
        int fa=Find(a);
        int fb=Find(b);
        if(fa!=fb) {
            //아래숫자를 루트로 설정
            unf[fb]=fa;
            //루트에 sum을 저장
            count[fa] += count[fb];
            //count[fb] = count[fa];
        }
        System.out.println(count[fa]);
    }
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        while(t-->0){
            int n=Integer.parseInt(br.readLine());
            unf= new int[n*2];
            count = new int[n*2];
            Arrays.fill(count,1);
            Hashtable<String,Integer> table=new Hashtable<>();
            for(int i=0;i<n*2;i++){
                unf[i]=i;
            }
            int index=0;
            for(int i=0;i<n;i++){
                String[] input=br.readLine().split(" ");
                if(!table.containsKey(input[0])) table.put(input[0],index++);
                if(!table.containsKey(input[1])) table.put(input[1],index++);
                int a=table.get(input[0]);
                int b=table.get(input[1]);
                Union(a,b);
            }
        }
    }
}