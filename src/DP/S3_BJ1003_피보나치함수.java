package DP;
import java.io.*;

/**
 * 16:14 시작
 * 16:45 끝
 * S3_BJ1003_피보나치함수
 */
public class S3_BJ1003_피보나치함수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int n=Integer.parseInt(br.readLine());
        int k;
        for(int i=0;i<n;i++) {
            int[][] fib={{1,0},{0,1}};
            k=Integer.parseInt(br.readLine());
            if(k==0)
                bw.write(fib[0][0]+" "+fib[0][1]+"\n");
            else if (k == 1)
                bw.write(fib[1][0]+" "+fib[1][1]+"\n");
            else{
                int[] tmp;
                for(int j=2;j<=k;j++){
                    tmp= new int[]{fib[1][0] + fib[0][0], fib[1][1] + fib[0][1]};
                    fib[0][0]=fib[1][0];
                    fib[0][1]=fib[1][1];
                    fib[1][0]=tmp[0];
                    fib[1][1]=tmp[1];
                }
                bw.write(fib[1][0]+" "+fib[1][1]+"\n");
            }

        }
        bw.flush();
        bw.close();
        br.close();

    }
}