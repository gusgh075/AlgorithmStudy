package BruteForce;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class S2_BJ2785_체인 {
     int N;
     int ring = 0;
     int min=Integer.MAX_VALUE;
     List<Integer> chains = new LinkedList<>();

    public  void sol(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        for (String string : s) {
            int num = Integer.parseInt(string);
            chains.add(num);
        }
        chains.sort(null);
        min=Math.min(min,N-1);
        int j=N;
        for(int i=0;i<j;i++){
            Integer cur = chains.get(i);
            ring+=cur;
            N--;
            if(ring==N-1){
                min=Math.min(min,N-1);
                break;
            }
            if(ring>N-1){
                min=Math.min(min,N);
                break;
            }
        }
        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
        br.close();
    }
}
