package Math;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 이 문제는 이전의 조이스틱에서 최솟값을 찾는 경우랑 비슷했다.
 *
 * 허나 유사점을 나중에 알아서 문제였다.
 *
 * 일단
 *
 * 1. 삭제할 값이 첫번째 INDEX보다 뒤에있는 경우
 * 2. 앞에있는경우
 *
 * 이 두개의 경우의 수에 대해 이동하는 최솟값을 정해야한다.
 *
 * 또한 자료형은 ARRAYLIST를 써서 삭제되면 INDEX가 자동으로 앞에가도록 했다
 */
public class S3_BJ1021_회전하는큐 {
    public static void sol() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n=Integer.parseInt(s[0]);
        int m=Integer.parseInt(s[1]);
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(i+1);
        }
        int now=0;
        s=br.readLine().split(" ");
        int ans=0;
        for(int i=0;i<m;i++){
            int a = Integer.parseInt(s[i]);
            int index=list.indexOf(a);
            if(now<=index) {
                ans = ans + Math.min(Math.abs(now + list.size() - index), Math.abs(index - now));
            }
            if(now>index) {
                ans = ans + Math.min(Math.abs(now - index), Math.abs(list.size() - now + index));
            }
            list.remove(index);
            if(!list.isEmpty()) {
                now = (index + list.size()) % list.size();
            }
        }
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }
}