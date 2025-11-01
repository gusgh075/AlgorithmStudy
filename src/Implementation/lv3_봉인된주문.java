package Implementation;

import java.util.*;
public class lv3_봉인된주문 {
    public String convertAlpha(long num){
        String ans="";
        while(num>0){
            num--;
            ans=(char)('a'+num%26)+ans;
            num/=26;
        }
        return ans;
    }
    public long convertNum(String alpha){
        long num=0;
        for(int i=0;i<alpha.length();i++){
            num+=Math.pow(26,alpha.length()-i-1)*(alpha.charAt(i)-'a'+1);
        }
        return num;
    }
    public String solution(long n, String[] bans) {
        String answer = "";
        long[] banNums=new long[bans.length];
        for(int i=0;i<banNums.length;i++){
            banNums[i]=convertNum(bans[i]);
        }
        Arrays.sort(banNums);
        for(long bN : banNums)
            if(n>=bN) n++;
        answer=convertAlpha(n);
        return answer;
    }
}