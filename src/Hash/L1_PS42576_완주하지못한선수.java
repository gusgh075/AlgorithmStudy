package Hash;

import java.util.HashMap;
import java.util.Map;

public class L1_PS42576_완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {

        String answer = "";
        Map<String,Integer> pMap= new HashMap<>();
        for(String p:participant){
            if(pMap.containsKey(p)) pMap.replace(p,pMap.get(p)+1);
            else pMap.put(p,1);
        }
        for(String c:completion){
            if(pMap.containsKey(c)){
                Integer cnt = pMap.get(c);
                if(cnt==1) pMap.remove(c);
                else pMap.replace(c,cnt-1);
            }
            else {answer=c;break;}
        }
        answer= (String) pMap.keySet().toArray()[0];
        return answer;
    }
}
