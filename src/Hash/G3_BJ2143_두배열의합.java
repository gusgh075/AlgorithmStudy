package Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
/*
부분합의 최대개수는 1000(1000+1)/2 의 제곱이다.
그러므로 ans의 자료형은 long이다.
 */
public class G3_BJ2143_두배열의합 {
    public static void calcWindow(long[][] win, long[] array, HashMap<Long, Long> HashMap) {
        int n = win.length;
        for (int i = 0; i < n; i++) {
            win[i][i] = array[i];
            HashMap.put(win[i][i],HashMap.getOrDefault(win[i][i], 0L) + 1);
            for (int j = i + 1; j < n; j++) {
                win[i][j] = win[i][j - 1] + array[j];
                HashMap.put(win[i][j], HashMap.getOrDefault(win[i][j], 0L) + 1);
            }
        }
    }

    public static long calcAnswer(long target, HashMap<Long, Long> fstHash, HashMap<Long, Long> sndHash) {
        long ans = 0;
        for (Long l : fstHash.keySet()) {
            ans = ans + fstHash.get(l) * sndHash.getOrDefault(target - l, 0L);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long t = Long.parseLong(br.readLine());
        br.readLine();
        long[] fst = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        br.readLine();
        long[] snd = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[][] fstWin = new long[fst.length][fst.length];
        long[][] sndWin = new long[snd.length][snd.length];
        HashMap<Long, Long> fstHash = new HashMap<>();
        HashMap<Long, Long> sndHash = new HashMap<>();
        calcWindow(fstWin, fst, fstHash);
        calcWindow(sndWin, snd, sndHash);
        System.out.println(calcAnswer(t,fstHash, sndHash));
    }
}
