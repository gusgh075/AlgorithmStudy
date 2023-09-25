package Math;

public class n진수게임 {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int cur=0;
        int loc=1;
        int loop=1;
        while(true){
            String num=convenrt(n,cur);
            for(char i:num.toCharArray()){
                if(loc==p) {
                    answer = answer + i;
                }
                if(loop==t&&loc==p) return answer;
                loc++;
                if(loc>m){loc=1;loop++;}
            }
            cur++;
        }
    }
    public String convenrt(int n, int cur){
        String num="";
        while(cur>=n){
            int rest=cur%n;
            if(rest>=10){
                rest+=55;
                num=(char)rest+num;
            }
            else{
                num=rest+num;
            }
            int mok=cur/n;
            cur=mok;
        }
        if(cur>=10){
            cur+=55;
            num=(char)cur+num;
        }
        else{
            num=cur+num;
        }
        return num;
    }
}
