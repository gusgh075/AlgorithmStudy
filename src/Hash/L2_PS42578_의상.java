package Hash;

import java.util.HashMap;
import java.util.Map;

/*
옷 종류와 이름이 주어짐
각 종류별 1가지 의상만 입을 수 있음
의상 전체 착장이 겹치면 안됨
최소 1개는 입음
-고려해야 할 점
각 종류의 의상을 어떤 자료구조에 넣어야 할 지
    HashTable<String,Integer>에 <의상종류, 개수>를 넣기
경우의 수를 어떻게 계산할 지
    if) 3옷 3바지 3선글라스 => 4*4*4-1 / -1은 아무것도 선택하지 않는 경우
 */
public class L2_PS42578_의상 {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> wear=new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            int cnt=1;
            String category = clothes[i][1];
            if(wear.containsKey(category)) cnt=wear.get(category)+1;
            wear.put(category,cnt);
        }
        for (Integer value : wear.values()) {
            answer*=(value+1);
        }
        answer--;
        return answer;
    }
}
