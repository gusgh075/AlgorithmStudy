package Math;
import java.io.*;

public class S3_BJ1002_터렛 {
    static int n;
    static double[] a=new double[3];
    static double[] b=new double[3];
    public static void sol1()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n=Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            String[] split = br.readLine().split(" ");
            for(int j=0;j<3;j++){
                a[j]=Double.parseDouble(split[j]);
            }
            for(int j=3;j<6;j++){
                b[j-3]=Double.parseDouble(split[j]);
            }
            bw.write(sol(a[0],a[1],a[2],b[0],b[1],b[2])+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    public static int sol(double x1, double y1, double r1, double x2, double y2, double r2){
        if(x1==x2&&y1==y2&&r1==r2)
            return -1;
        double dist=calDist(x1,y1,x2,y2);
        if(dist<=r1||dist<=r2){
            if(dist+r1<r2||dist+r2<r1){
                return 0;
            }
            if(dist+r1==r2 || dist+r2==r1){
                return 1;
            }
            if(dist+r1>r2||dist+r2>r1){
                return 2;
            }
        }
        if(dist>=r1&&dist>=r2){
            if(dist>r1+r2)
                return 0;
            if(dist==r1+r2)
                return 1;
            if(dist<r1+r2)
                return 2;
        }
        return 10;
    }
    public static double calDist(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }
}