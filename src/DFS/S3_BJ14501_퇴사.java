package DFS;
import java.io.*;

public class S3_BJ14501_퇴사 {
    static int n;
    static int[][] consult;
    static int ans=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        n=Integer.parseInt(br.readLine());
        consult =new int[n][2];
        for(int i=0;i<n;i++){
            String[] split = br.readLine().split(" ");
            for(int j=0;j<2;j++){
                consult[i][0]=Integer.parseInt(split[0]);
                consult[i][1]=Integer.parseInt(split[1]);
            }
        }
        for(int i=0;i<n;i++){
            if(i+consult[i][0]-1>=n){
                consult[i][1]=0;
            }
        }
        dfs(0,0);
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }
    public static void dfs(int now, int money){
        if(now>=n){
            ans=Math.max(ans,money);
            return;
        }
        dfs(now+1,money);
        dfs(now+consult[now][0],money+consult[now][1]);
    }

}
