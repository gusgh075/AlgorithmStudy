package Math;

import java.io.*;

//G5_BJ1117_색칠1
public class G5_BJ1117_색칠1 {
    public static void sol() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] a = br.readLine().split(" ");
        long[] s=new long[a.length];
        for(int i=0;i<s.length;i++){
            s[i]=Long.parseLong(a[i]);
        }
        long w=s[0];
        long h=s[1];
        long f=s[2];
        long c=s[3];
        long x1=s[4];
        long y1=s[5];
        long x2=s[6];
        long y2=s[7];
        long ans=w*h;
        //우측에 더 큰 사각형이 가도록
        f=Math.min(f,w-f);
        //접은 우측에 칠한 공간
        ans=ans-(x2-x1)*(y2-y1)*(c+1);
        //접은 왼측에 칠한 공간
        x1=f-x1;
        x2=f-x2;
        ans=ans-(Math.max(x1, 0)-Math.max(x2, 0))*(y2-y1)*(c+1);
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}