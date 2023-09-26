package Math;

import java.util.LinkedList;
import java.util.Queue;

public class lv2_프로세스 {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer> queue=new LinkedList<>();

        for(int i:priorities)
            queue.add(i);
        int index=location;

        a:while(true){
            int num=queue.poll();
            if(index==0){
                for(int i=num+1;i<=9;i++){
                    if(queue.contains(i)){
                        queue.add(num);
                        index=queue.size()-1;
                        continue a;
                    }
                }
                answer++;
                return answer;
            }
            else {
                for (int i = num + 1; i <= 9; i++) {
                    if (queue.contains(i)) {
                        queue.add(num);
                        index--;
                        continue a;
                    }
                }
                answer++;
                index--;
            }
        }



    }
}
