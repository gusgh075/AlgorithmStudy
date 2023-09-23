package Greedy;

import java.io.*;
//48분 1041  주사위
class G4_BJ1041_주사위 {
    public static void solution() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine());

        int[] dice=new int[6];
        {
            String[] tmp=br.readLine().split(" ");
            for(int i=0;i<6;i++){
                dice[i]=Integer.parseInt(tmp[i]);
            }
        }
        if(n==1){
            int max=0;
            int answer=0;
            for(int i=0;i<6;i++){
                answer+=dice[i];
                max=Math.max(max,dice[i]);
            }
            bw.write((answer-max)+"");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        //n이 2이상일때 상황
        final int thr=4;
        final int two=8*n-12;
        final long one=(long)(n-2)*(n-1)*4+ (long) (n - 2) *(n-2);

        int thrMin=Integer.MAX_VALUE;
        int twoMin=Integer.MAX_VALUE;
        int oneMin=Integer.MAX_VALUE;

        for(int i=0;i<6;i++){
            oneMin=Math.min(oneMin,dice[i]);
            for(int j=i+1;j<6;j++){
                if((i+j)==5){
                    continue;
                }
                twoMin=Math.min(twoMin,dice[i]+dice[j]);
                for(int k=j+1;k<6;k++){
                    if((i+k)==5||(j+k)==5){
                        continue;
                    }
                    thrMin=Math.min(thrMin,dice[i]+dice[j]+dice[k]);
                }
            }
        }
        bw.write((oneMin*one+ (long) twoMin *two+ (long) thrMin *thr)+"");
        bw.flush();
        br.close();
        bw.close();
    }

}