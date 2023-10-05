package Greedy;
import java.io.*;
import java.util.*;

public class G5_BJ1092_배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> crane = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> box = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        if (box.get(0) > crane.get(0)) {
            bw.write(-1 + "");
            bw.flush();
            bw.close();
            br.close();
            return;
        }
        int ans = 0;
        while (!box.isEmpty()) {
//                수정했음에도 시간초과 오류가 생기기에 위의 코드에 문제가 있다 판단
            int idx = 0;
            for (int i = 0; i < n; ) {
                if (idx == box.size()) break;
                else if (crane.get(i) >= box.get(idx)) {
                    box.remove(idx);
                    i++;
                } else idx++;
            }
            ans++;
//                아래의 방법은 더 복잡하게 구현해야 하기에, 위와같이 idx 변수를 통해 구현해보자
//                ans++;
//                int k=0;
//                a:for (int i = 0; i < n; i++) {
//                    for (int j = k; j <= box.size()-1; j++) {
//                        if (crane.get(i) >= box.get(j)) {
//                            k=j;
//                            box.remove(j);
//                            if(j==box.size())
//                                break a;
//                            break;
//                        }
//                        else if(j==box.size()-1)
//                            break a;
//                    }
//                }


        }
        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
