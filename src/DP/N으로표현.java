package DP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class N으로표현 {
    public int solution(int N, int number) {
        if(N==number)
            return 1;
        //Set은 중복을 허락하지 않고, 순서가 없다
        ArrayList<Set<Integer>> dp =new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        dp.add(new HashSet<>());
        for(int i=1;i<=8;i++){
            dp.add(new HashSet<>());
            dp.get(i).add(Integer.parseInt(sb.append(N).toString()));
        }
        for(int i=2;i<=8;i++){
            for(int j=1;j<i;j++){
                int k=i-j;
                for (Integer num1 : dp.get(j)) {
                    for (Integer num2 : dp.get(k)) {
                        dp.get(i).add(num1+num2);
                        dp.get(i).add(num1-num2);
                        dp.get(i).add(num1*num2);
                        if(num2!=0){
                            dp.get(i).add(num1/num2);
                        }
                    }
                }
            }
            if(dp.get(i).contains(number))
                return i;
        }
        return -1;
    }
}