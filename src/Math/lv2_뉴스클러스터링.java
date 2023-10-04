package Math;

import java.util.ArrayList;
import java.util.Collections;

public class lv2_뉴스클러스터링 {
    public int solution(String str1, String str2) {
        int answer = 0;
        int min=0;
        int max=0;
        str1=str1.toLowerCase();
        str2=str2.toLowerCase();


        Character[] notAllow=new Character[]{' ','!','`','@',};
        ArrayList<String> firstList = new ArrayList<>();
        ArrayList<Object> secondList = new ArrayList<>();
        for(int i=0;i<str1.length()-1;i++){
            String fsts = str1.substring(i, i + 2);
            if(fsts.substring(0,1).matches(".*[a-z].*")&&fsts.substring(1,2).matches(".*[a-z].*")) {
                firstList.add(fsts);
            }
        }
        for(int i=0;i<str2.length()-1;i++){
            String scds = str2.substring(i, i + 2);
            if(scds.substring(0,1).matches(".*[a-z].*")&&scds.substring(1,2).matches(".*[a-z].*")) {
                secondList.add(scds);
            }
        }
        if(firstList.isEmpty()&&secondList.isEmpty())
            return 65536;

        while(!firstList.isEmpty()){
            String now = firstList.get(0);
            if(secondList.contains(now)){
                int fst=Collections.frequency(firstList,now);
                int scd=Collections.frequency(secondList,now);
                min=min+Math.min(fst,scd);
                max=max+Math.max(fst,scd);
                while(firstList.remove(now)){}
                while(secondList.remove(now)){};
            }
            else{
                max+=Collections.frequency(firstList,now);
                while(firstList.remove(now)) {}
            }
        }
        max=max+secondList.size();
        answer=(int)(min/(double)max*65536);
        return answer;
    }
}
