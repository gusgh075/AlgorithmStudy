package Hash;

import java.util.HashSet;
import java.util.Set;

/*
N마리 중 N/2 가져갈 수 있음
    종류에 따라 번호 붙여 구분 => 같은 종류는 같은 번호
        고르는 경우는 조합이다
목적 : 최대한 다양한 종류의 폰켓몬 고르기
방법 : 해쉬셋 만들어서 폰켓몬 종류가 몇개인지 확인하고,
        골라야할 폰켓몬 개수인 n/2보다 크면 n/2를 반환,
        아니라면 hashSet 요소 개수를 반환
 */
public class L1_PS1845_폰켓몬 {
    public int solution(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int answer= Math.min(hashSet.size(), nums.length / 2);
        return answer;
    }
}
