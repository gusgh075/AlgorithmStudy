import java.awt.*;
import java.io.*;
import java.util.*;

/*
land[][] 중 꼭짓점 하나가 겹치는 정사각형 2개를 고른다
그리고 이 정사각형 2개 내부의 합은 같아야 한다
크기는 달라도 된다
이때 경우의 수는?

시간복잡도 O(n^4) => n의 최대값은 50

gpt의 도움 ㅇ => for문을 얼마나 돌릴 것인지 + 누적합 개념 도움받음

느낀점 : 전처리 과정(ex.누적합)이 중요하다! 데이터를 어떻게 전처리 할것인지 생각해보자.
 */
public class G1_BJ1184_귀농 {
    static int[][] pSum;
    static int n;
    public static int addSum(int y1, int x1, int y2, int x2) {
        Point lu = new Point(Math.min(x1, x2), Math.min(y1, y2));
        Point rd = new Point(Math.max(x1, x2), Math.max(y1, y2));
        return pSum[rd.y][rd.x]
                - (lu.y > 0 ? pSum[lu.y - 1][rd.x] : 0)
                - (lu.x > 0 ? pSum[rd.y][lu.x - 1] : 0)
                + (lu.y > 0 && lu.x > 0 ? pSum[lu.y - 1][lu.x - 1] : 0);
    }

    public static Map<Integer, Integer> searchLU(int x, int y){
        //LeftUp
        Map<Integer, Integer> lu = new HashMap<>();
        for (int w = 1; x - w >= 0; w++) {
            for (int h = 1; y - h >= 0; h++) {
                int sum = addSum(y - h, x - w, y - 1, x - 1);
                lu.put(sum, lu.getOrDefault(sum, 0) + 1);   //rd가 lu에 있는지 확인할거임
            }
        }
        return lu;
    }
    public static Map<Integer,Integer> searchLD(int x, int y){
        Map<Integer, Integer> ld = new HashMap<>();
        for (int w = 1; x - w >= 0; w++) {
            for (int h = 1; y + h <= n; h++) {
                int sum = addSum(y + h - 1, x - w, y, x - 1);
                ld.put(sum, ld.getOrDefault(sum, 0) + 1);
            }
        }
        return ld;
    }
    public static Map<Integer,Integer> searchRU(int x, int y){
        Map<Integer, Integer> ru = new HashMap<>();
        for (int w = 1; x + w <= n; w++) {
            for (int h = 1; y - h >= 0; h++) {
                int sum = addSum(y - h, x + w - 1, y - 1, x);
                ru.put(sum, ru.getOrDefault(sum, 0) + 1);
            }
        }
        return ru;
    }
    public static Map<Integer,Integer> searchRD(int x, int y){
        Map<Integer, Integer> rd = new HashMap<>();
        for (int w = 1; x + w <= n; w++) {
            for (int h = 1; y + h <= n; h++) {
                int sum = addSum(y + h - 1, x + w - 1, y, x);
                rd.put(sum, rd.getOrDefault(sum, 0) + 1);
            }
        }
        return rd;
    }

    public static int findSameRec(int x,int y){
        int num=0;
        Map<Integer, Integer> lu = searchLU(x, y);
        Map<Integer, Integer> ru = searchRU(x, y);
        Map<Integer, Integer> ld = searchLD(x, y);
        Map<Integer, Integer> rd = searchRD(x, y);
        for (Integer i : lu.keySet()) {
            num+=rd.getOrDefault(i,0)*lu.get(i);
        }
        for (Integer i : ld.keySet()) {
            num+=ru.getOrDefault(i,0)*ld.get(i);
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] land = new int[n][n];
        pSum = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                land[i][j] = input[j];
            }
        }
        //(0,0)-(x,y) 사각형의 합
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                pSum[y][x] = land[y][x];
                pSum[y][x] = pSum[y][x]
                        + (y > 0 ? pSum[y - 1][x] : 0)
                        + (x > 0 ? pSum[y][x - 1] : 0)
                        - (x > 0 && y > 0 ? pSum[y-1][x-1] : 0);
            }
        }
        int ans = 0;
        //(0,0)~(n+1,n+1) 꼭짓점을 조회 => x,y가 0,n+1일 때는 조회하지 않음
        //                                  bcs. 대각선 4방향을 조사해야 되는데, 불가능한 좌표이기 때문
        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= n; x++) {
                ans+=findSameRec(x,y);
            }
        }
        System.out.println(ans);
    }
}
