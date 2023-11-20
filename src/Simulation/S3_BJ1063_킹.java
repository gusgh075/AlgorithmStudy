package Simulation;

import java.io.*;
import java.util.Arrays;

public class S3_BJ1063_킹 {
    static int[][] board = new int[8][8];

    public static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int[] tmp :
                board) {
            Arrays.fill(tmp, 0);
        }
        String[] s = br.readLine().split(" ");
        int[] king=new int[2];
        int[] stone=new int[2];
        char[] c1 = s[0].toCharArray();
        char[] c2 = s[1].toCharArray();
        king[0]=(int)c1[0]-65;
        king[1]=Character.getNumericValue(c1[1])-1;
        stone[0]=(int)c2[0]-65;
        stone[1]=Character.getNumericValue(c2[1])-1;
        int n=Integer.parseInt(s[2]);
        for(int i=0;i<n;i++){
            String dir = br.readLine();
            int[] kclone = king.clone();
            int[] sclone = stone.clone();
            if(dir.equals("R")){
                kclone[0]++;
                if(kclone[0]==sclone[0]&&kclone[1]==sclone[1]){
                    sclone[0]++;
                }
            }
            if(dir.equals("L")){
                kclone[0]--;
                if(kclone[0]==sclone[0]&&kclone[1]==sclone[1]){
                    sclone[0]--;
                }
            }
            if(dir.equals("B")){
                kclone[1]--;
                if(kclone[0]==sclone[0]&&kclone[1]==sclone[1]){
                    sclone[1]--;
                }
            }
            if(dir.equals("T")){
                kclone[1]++;
                if(kclone[0]==sclone[0]&&kclone[1]==sclone[1]){
                    sclone[1]++;
                }
            }
            if(dir.equals("RT")){
                kclone[0]++;
                kclone[1]++;
                if(kclone[0]==sclone[0]&&kclone[1]==sclone[1]){
                    sclone[0]++;
                    sclone[1]++;
                }
            }
            if(dir.equals("LT")){
                kclone[0]--;
                kclone[1]++;
                if(kclone[0]==sclone[0]&&kclone[1]==sclone[1]){
                    sclone[0]--;
                    sclone[1]++;
                }
            }
            if(dir.equals("RB")){
                kclone[0]++;
                kclone[1]--;
                if(kclone[0]==sclone[0]&&kclone[1]==sclone[1]){
                    sclone[0]++;
                    sclone[1]--;
                }
            }
            if(dir.equals("LB")){
                kclone[0]--;
                kclone[1]--;
                if(kclone[0]==sclone[0]&&kclone[1]==sclone[1]){
                    sclone[0]--;
                    sclone[1]--;
                }
            }

            if(kclone[0]>=8||kclone[0]<0||
                    kclone[1]>=8||kclone[1]<0||
                    sclone[0]>=8||sclone[0]<0||
                    sclone[1]>=8||sclone[1]<0){

            }
            else{
                king=kclone.clone();
                stone=sclone.clone();
            }

        }
        bw.write((char)(king[0]+65)+""+(king[1]+1)+"\n");
        bw.write((char)(stone[0]+65)+""+(stone[1]+1));
        bw.flush();
        bw.close();
        br.close();

    }
}
