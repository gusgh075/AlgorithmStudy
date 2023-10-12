import java.io.*;

//S3_BJ1072_게임
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s = br.readLine().split(" ");
        long x = Integer.parseInt(s[0]);
        long y = Integer.parseInt(s[1]);
        long n=y*100/x;
        long a=x-y;
        if(n>=99)
            bw.write("-1");
        else {
            long one=99-n;
            long two=100*a;
            long mid=two/one;
            if (two % one == 0) {
                bw.write(String.valueOf(mid));
            } else {
                bw.write(String.valueOf(mid + 1));
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}