package Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_BJ1083_소트{
    public int[] num;
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        int s = Integer.parseInt(br.readLine());

        int current=0;
        while(s>0&&current<=n){
            int[] max=new int[n];
            Arrays.fill(max,-1);
            int maxNum=0;
            for(int i=current;i<n;i++){
                if(num[i]>maxNum){
                    max[i]=num[i];
                    maxNum=num[i];
                }
            }
            for(int i=n-1;i>current;i--){
                if(max[i]!=-1&&i-current<=s){
                    for(int j=i;j>current;j--){
                        int tmp=num[j];
                        num[j]=num[j-1];
                        num[j-1]=tmp;
                    }
                    s=s-i+current;
                    break;
                }
            }
            current++;
        }
        for(int ans:num){
            bw.write(ans+" ");
        }
        bw.flush();
        bw.close();
        br.close();


    }

}
