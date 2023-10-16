import java.io.*;
import java.util.Arrays;

//G5_BJ1107_리모컨
public class Main {
    static String n;
    static int m;
    static boolean[] num = new boolean[10];
    static String result = "";
    static int ans;
    static int index;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = br.readLine();
        m = Integer.parseInt(br.readLine());
        Arrays.fill(num, true);
        if (m != 0) {
            String[] s = br.readLine().split(" ");
            for (int i = 0; i < m; i++) {
                num[Integer.parseInt(s[i])] = false;
            }
        }
        ans = Math.abs(Integer.parseInt(n) - 100);
        for (int i = 0; i < n.length(); i++) {
            int cur = Integer.parseInt(n.substring(i, i + 1));
            if (num[cur]) {
                result += cur;
                index++;
            } else {
                break;
            }
            if(i==n.length()-1){
                ans = Math.min(ans, Math.abs(Integer.parseInt(result) - Integer.parseInt(n)) + n.length());
                bw.write(String.valueOf(ans));
                bw.flush();
                bw.close();
                br.close();
                return;
            }
        }
        int cur = Integer.parseInt(n.substring(index, index+1));
        boolean tmp = true;
        int min = 0;
        int max = 0;
        for (int i = 0; i <= 9; i++) {
            if (num[i]) {
                min = i;
                break;
            }
        }
        for (int i = 9; i >= 0; i--) {
            if (num[i]) {
                max = i;
                break;
            }
        }
        int i = 1;
        while (tmp) {
            if (cur + i <= 9 && num[cur + i]) {
                result += (cur + i);
                for (int j = result.length(); j < n.length(); j++) {
                    result += min;
                }
                ans = Math.min(ans, Math.abs(Integer.parseInt(result) - Integer.parseInt(n)) + n.length());
                result = result.substring(0, index + 1);
                tmp = false;
            }
            if (cur - i >= 0 && num[cur - i]) {
                result += (cur - i);
                for (int j = result.length(); j < n.length(); j++) {
                    result += max;
                }
                ans = Math.min(ans, Math.abs(Integer.parseInt(result) - Integer.parseInt(n)) + n.length());
                result = result.substring(0, index + 1);
                tmp = false;
            }
            i++;
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();

    }
}