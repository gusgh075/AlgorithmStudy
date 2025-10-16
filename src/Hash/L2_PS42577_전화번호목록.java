package Hash;

import java.util.Arrays;

/* 직접 풀어봤을 때
1. 숫자배열이 주어짐
2. 배열 요소중 A가 B의 앞부분이라면 false, 앞부분인 요소가 없다면 true
제한 사항
1. 배열 길이는 1~1,000,000 => BF로 풀면 시간 초과 뜰거임
2. 각 요소의 길이는 1~20
3. 같은 요소가 중복으로 없음
해결 방법
1. 길이별로 정렬하고 조회하면, 순차적으로 검증 가능
2. 커스텀 클래스 Num을 생성해서, ComparTo를 길이별로 정렬하도록 설정
3. ArrayList<Num>에 전체 요소를 넣고 정렬함
4. 순차적으로 조회하며 최소길이 min을 사용해서 subString(0,min~끝)이 set에 있으면 return false
5. 없으면 set에 추가
6. 전체 조회한 뒤 return true;
시간 복잡도
전체 조회 O(N) => 1,000,000
문자열 HashSet 삽입 O(N) => 20*1,000,000

 */
/* GPT의 조언
String.startsWith(String s)로 접두사 파악 가능
Stirng[]을 정렬하면 사전순으로 정렬 => 길이별 정렬이 아니여도 괜찮음. 사전순으로 정렬한 뒤 조사해도 가능
1. phone_book 정렬
2. phone_book[i+1]이 phone_book[i]로 시작하는지 확인
 */
public class L2_PS42577_전화번호목록 {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length-1; i++) {
            if(phone_book[i+1].startsWith(phone_book[i]))return false;
        }
        return true;
    }
}
