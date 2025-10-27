import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/*
자연수의 집합 U
U 내부의 요소 중에는 a+b+c=d와 같은 세 요소의 합이 다른 요소의 값과 같은 경우가 있다.
    a+a+a=e여도 상관 없다.
이 때, d가 가장 큰 경우를 찾자
- 풀이 방법
이 문제는 조합이다.
이 문제는 여러 쌍을 조사한다.
모든 쌍을 BF로 조사하는 경우. 1000의 3제곱이다(중복포함)


핵심
    3중 for문을 이분탐색으로 변경할 수 있는가?
 */
public class G4_BJ2295_세수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(br.readLine());
            hashSet.add(array[i]);
        }
        Arrays.sort(array);
        ArrayList<Integer> twoSum = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                twoSum.add(array[i] + array[j]);
            }
        }
        Collections.sort(twoSum);

        for (int d = N - 1; d >= 0; d--) {
            for (int c = d; c >= 0; c--) {
                int target = array[d] - array[c];
                if (Collections.binarySearch(twoSum, target) >= 0) {    //Collections.binarySearch는 정렬된 리스트 안에서 logN의 시간복잡도를 가짐
                    System.out.println(array[d]);
                    return;
                }
            }
        }
    }
}
