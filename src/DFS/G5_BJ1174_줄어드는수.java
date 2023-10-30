package DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class G5_BJ1174_줄어드는수 {
    static int n;
    static int[] arr={9,8,7,6,5,4,3,2,1,0};
    static List<Long> list= new ArrayList<>();
    public static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n=Integer.parseInt(br.readLine());
        dfs(0,0);
        list.sort(null);
        try{
            bw.write(String.valueOf(list.get(n-1)));
        }catch(Exception e){
            bw.write("-1");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    public static void dfs(long num, int index){
        if(!list.contains(num)){
            list.add(num);
        }
        if(index>=10){
            return;
        }
        dfs(num*10+arr[index],index+1);
        dfs(num,index+1);
    }
}