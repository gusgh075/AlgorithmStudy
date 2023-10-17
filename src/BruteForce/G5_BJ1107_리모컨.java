package BruteForce;

import java.io.*;
import java.util.Arrays;

public class G5_BJ1107_리모컨 {
    static String n;
    static boolean[] num = new boolean[10];
    static int ans;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void sol() throws IOException {
        n=br.readLine();
        int m=Integer.parseInt(br.readLine());
        Arrays.fill(num,true);
        if(m!=0) {
            String[] s = br.readLine().split(" ");
            for (int i = 0; i < m; i++) {
                num[Integer.parseInt(s[i])]=false;
            }
        }
        ans=Math.abs(100-Integer.parseInt(n));
        if(ans==0)
            bw.write(String.valueOf(ans));
        else {
            char[] tmp;
            a:
            for (int i = 0; i <= 999999; i++) {
                tmp = String.valueOf(i).toCharArray();
                for (char a : tmp) {
                    if (!num[Character.getNumericValue(a)]) {
                        continue a;
                    }
                }
                ans = Math.min(ans, Math.abs(Integer.parseInt(n) - i) + String.valueOf(i).length());
            }
            bw.write(String.valueOf(ans));
        }
        bw.flush();
        bw.close();
        br.close();

    }

}
