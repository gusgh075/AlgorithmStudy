package BruteForce;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

//객체지향으로
//함수목록
//string 알파벳 요소별로 충족되는지
//정답배열에 알파벳 하나씩만 +1하기, char 리스트 입력받든지 하기
public class G5_BJ1148_단어만들기 {
    static ArrayList<int[]> words=new ArrayList<>();
    static int[] tmp=new int[26];
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true){
            String word=br.readLine();
            if(word.equals("-")){
                break;
            }
            words.add(chkAlp(word));
        }
        int[] map;
        int[] ans=new int[26];
        int[] tans;
        while(true){
            String line=br.readLine();
            if(line.equals("#")){
                break;
            }
            Arrays.fill(ans,0);
            map = chkAlp(line);
            for(int[] word:words){
                tans=compareLine(map,word);
                if(tans.length==1)
                    continue;
                for(int i=0;i<26;i++){
                    ans[i]+=tans[i];
                }
            }
            bw.write(chkMinMax(ans,map));
        }
        bw.flush();
        bw.close();
        br.close();
    }
    public static int[] chkAlp(String line){
        Arrays.fill(tmp,0);
        for(char ch:line.toCharArray()){
            tmp[(int)ch-65]++;
        }
        return tmp.clone();
    }
    public static int[] compareLine(int[] map,int[] word){
        Arrays.fill(tmp,0);
        for(int i=0;i<26;i++){
            if (map[i] < word[i])
                return new int[]{-1};
            if(word[i]!=0) {
                tmp[i] = 1;
            }
        }
        return tmp;
    }
    public static String chkMinMax(int[] ans,int[] map){
        int min=Integer.MAX_VALUE;int max=0;
        String minCh="";String maxCh="";
        for(int i=0;i<26;i++){
            if(map[i]>0) {
                min = Math.min(ans[i], min);
                max = Math.max(ans[i], max);
            }
        }
        for(int i=0;i<26;i++){
            if(map[i]>0) {
                if (min == ans[i]) {
                    minCh = minCh + (char) (i + 65);
                }
                if (max == ans[i]) {
                    maxCh = maxCh + (char) (i + 65);
                }
            }
        }
        return minCh+" "+min+" "+maxCh+" "+max+"\n";
    }
}
