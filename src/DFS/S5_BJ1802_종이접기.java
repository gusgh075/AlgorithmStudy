package DFS;
import java.io.*;
import java.util.Arrays;

public class S5_BJ1802_종이접기 {
    public  void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            String line = br.readLine();
            int length = line.length();
            int[] numbers=new int[length];
            for(int j=0;j<length;j++){
                numbers[j]=Character.getNumericValue(line.charAt(j));
            }
            if(DFS(numbers)){
                bw.write("YES\n");
            }
            else{
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    public  boolean DFS(int[] now){
        if(now.length<=2){
            return true;
        }
        if(!checkSide(now))
            return false;
        if(DFS(Arrays.copyOfRange(now,0,now.length/2))&&
                DFS(Arrays.copyOfRange(now,now.length/2+1,now.length))){
            return true;
        }
        else{
            return false;
        }
    }
    public  boolean checkSide(int[] now){
        int divider=now.length/2;
        for(int i=1;i<=divider;i++){
            if(now[divider-i]==now[divider+i])
                return false;
        }
        return true;
    }
}