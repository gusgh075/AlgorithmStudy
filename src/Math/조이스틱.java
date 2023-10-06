package Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 조이스틱 {
    public  int solution(String name) {
        int answer = 0;
        int A = 'A';
        int Z = 'Z';
        int dest = Integer.MAX_VALUE;
        List<Integer> index = new ArrayList<>();

        char[] cName = name.toCharArray();
        int[] count = new int[name.length()];

        for (int i = 0; i < count.length; i++) {
            count[i] = Math.min(cName[i] - A, Z - cName[i] + 1);
            if (count[i] != 0 && i != 0) {
                index.add(i);
            }
            answer += count[i];
        }

        if (!index.isEmpty()) {
            for (int i = 0; i < index.size() - 1; i++) {
                dest = Math.min(index.get(i) * 2 + (count.length - index.get(i + 1)), dest);
                dest = Math.min(index.get(i) + (count.length - index.get(i + 1)) * 2, dest);
                System.out.printf("%d dest %d\n", i, dest);
            }
            dest = Math.min(index.get(index.size() - 1), dest);
            System.out.printf("left dest %d\n", dest);
            dest = Math.min(count.length - index.get(0), dest);
            System.out.printf("right dest %d\n", dest);
            answer += dest;
        }
        System.out.printf("dest %d\ncount[i] %s\nanswer %d\n", dest, Arrays.toString(count), answer);

        return answer;
    }
}
