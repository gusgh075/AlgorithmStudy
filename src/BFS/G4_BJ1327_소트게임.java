package BFS;

import java.util.*;

/*
2025.10.12.09:50 시작
소트게임 1~N 정수
K가 주어짐
어떤 수 뒤집으면 그 수부터 오른쪽으로 K개의 수를 뒤집어야됨
뒤집는다 = 역순으로 둔다

목표 = 입력으로 들어온 순열을 오름차순으로 만들려고 함

어차피 오른쪽으로 뒤집음
=> 맨 처음부터 시작해서 정답이 나올때까지 돌려보기
 */
public class G4_BJ1327_소트게임 {
    public static class Pair {
        String s;
        int cnt;

        Pair(String s, int cnt) {
            this.s = s;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine().replace(" ","");

        int[] array = new int[n];
        for (int i = 0; i<n; i++) {
            array[i] = s.charAt(i)-'0';
        }
        Arrays.sort(array);
        String target = "";
        for (int i = 0; i < array.length; i++) {
            target += (array[i] + "");
        }

        Set<String> hashSet = new HashSet<>();
        hashSet.add(s);
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(s, 0));
        int ans=-1;
        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.s.equals(target)) {
                ans=p.cnt;
                break;
            }
            for (int i = 0; i < n; i++) {
                if (i + k - 1 >= n)
                    break;
                String nxS = swap(p.s, i, k);
                if (!hashSet.contains(nxS)) {
                    hashSet.add(nxS);
                    q.add(new Pair(nxS, p.cnt + 1));
                }
            }
        }
        System.out.println(ans);
    }

    public static String swap(String s, int i, int k) {
        String target = s.substring(i, i + k);
        char[] charArray = target.toCharArray();
        char[] charTarget = new char[k];
        for (int j = 0; j < k; j++) {
            charTarget[j]=charArray[k-j-1];
        }
        return s.substring(0, i) + String.valueOf(charTarget) + s.substring(i + k);
    }
}
