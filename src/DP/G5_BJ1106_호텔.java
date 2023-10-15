package DP;
import java.io.*;
import java.util.Arrays;

public class G5_BJ1106_호텔 {
    public static void sol()throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = br.readLine().split(" ");
        int m=Integer.parseInt(s[0]);
        int[] c = new int[m+101];
        int n=Integer.parseInt(s[1]);
        int[][] city=new int[n][2];
        for(int i=0;i<n;i++){
            s = br.readLine().split(" ");
            city[i][0]=Integer.parseInt(s[0]);
            city[i][1]=Integer.parseInt(s[1]);
        }
        Arrays.fill(c,Integer.MAX_VALUE);
        c[0]=0;
        for(int i=1;i<=m+100;i++){
            for(int j=0;j<n;j++){
                if(i-city[j][1]<0)
                    continue;
                if(c[i-city[j][1]]==Integer.MAX_VALUE)
                    continue;
                c[i]=Math.min(c[i],c[i-city[j][1]]+city[j][0]);
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=m;i<=m+100;i++){
            min=Math.min(min,c[i]);
        }
        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
        br.close();
    }
}