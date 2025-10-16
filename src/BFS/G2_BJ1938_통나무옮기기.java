package BFS;

import java.io.*;
import java.util.*;

/*
입력값
0 : 아무 것도 없음
1 : 잘려지지 않은 나무
B : 2
E : 3
B를 E로 옮겨야됨
! 통나무의 길이는 항상 3
! B와 E의 개수는 같음 => 통나무의 개수는 여러개일 수 있음
움직이는 방법
위, 아래, 왼쪽, 오른쪽, 중심점을 기준으로 90도 회전
=> 이때 움직이는 방향에 다른 나무(1)가 없어야 함
! 통나무는 대각선이 될 수 없음
! 회전시 3*3범위에 나무가 있어서는 안됨
 */
public class G2_BJ1938_통나무옮기기 {
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static class Log {
        int x;
        int y;
        boolean isVertical;

        public Log(int x, int y, boolean isVertical) {
            this.x = x;
            this.y = y;
            this.isVertical = isVertical;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (Log.class != obj.getClass()) return false;
            Log l = (Log) obj;
            return l.x == this.x
                    && l.y == this.y
                    && l.isVertical == this.isVertical;

        }

        @Override
        public int hashCode() {
            //동일 변수값 => 같은 해시값
            return Objects.hash(x, y, isVertical);
        }
    }

    public static class Pair {
        Log log;
        int cnt;

        public Pair(Log log, int cnt) {
            this.log = new Log(log.x, log.y, log.isVertical);
            this.cnt = cnt;
        }
    }

    /**
     * 해당 좌표가 지도 안에 있는지
     * @param map
     * @param x
     * @param y
     * @return
     */
    public static boolean inMap(int[][] map, int x, int y) {
        int n = map.length;
        return (n > x && n > y && x >= 0 && y >= 0);
    }

    /**
     * 통나무가 있는 위치가 가능한 위치인지
     *
     * @param map
     * @param log
     * @return
     */
    public static boolean canMove(int[][] map, Log log) {
        for (int i = 0; i < 4; i++) {
            if (log.isVertical) {
                int ny = log.y + dy[i];
                if (!inMap(map,log.x,ny)||map[ny][log.x] == 1) return false;
            } else {
                int nx = log.x + dx[i];
                if (!inMap(map,nx,log.y)||map[log.y][nx] == 1) return false;
            }
        }
        return true;
    }

    /**
     * 통나무가 회전 할 수 있는 위치인지
     *
     * @param map
     * @param log
     * @return
     */
    public static boolean canRotate(int[][] map, Log log) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nx = log.x + j;
                int ny = log.y + i;
                if (!inMap(map,nx,ny)||map[ny][nx] == 1) return false;
            }
        }
        return true;
    }

    /**
     * 통나무가 EEE에 있는지
     *
     * @param map
     * @param log
     * @return
     */
    public static boolean isEnd(int[][] map, Log log) {
        for (int i = 0; i < 4; i++) {
            if (log.isVertical) {
                int ny = log.y + dy[i];
                if (!inMap(map,log.x,ny)||map[ny][log.x] != 4) return false;
            } else {
                int nx = log.x + dx[i];
                if (!inMap(map,nx,log.y)||map[log.y][nx] != 4) return false;
            }
        }
        return true;
    }

    /**
     * 통나무를 이동시켜 EEE에 도착할 떄 까지
     *
     * @param map
     * @param log
     * @return
     */
    public static int BFS(int[][] map, Log log) {
        Queue<Pair> q=new ArrayDeque<>();
        Set<Log> set=new HashSet<>();
        q.add(new Pair(log,0));
        set.add(log);

        while(!q.isEmpty()){
            Pair cur = q.poll();
            if(isEnd(map,cur.log)) return cur.cnt;
            Log curL = cur.log;
            //상하좌우이동
            for (int i = 0; i < 4; i++) {
                int nx=curL.x+dx[i];
                int ny=curL.y+dy[i];
                Log newL = new Log(nx, ny, curL.isVertical);
                if(canMove(map,newL)&&!set.contains(newL)){
                    q.add(new Pair(newL,cur.cnt+1));
                    set.add(newL);
                }
            }
            //회전
            if(canRotate(map,curL)){
                Log newL=new Log(curL.x,curL.y,!curL.isVertical);
                if(!set.contains(newL)){
                    q.add(new Pair(newL,cur.cnt+1));
                    set.add(newL);
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        //데이터 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];//2차원 배열 => map을 계속 복제하지 않을거기 때문에 메모리상 문제 없음
        //입력받으며 초기 통나무 위치 설정
        int sumX = 0;
        int sumY = 0;
        boolean isV = true;
        for (int i = 0; i < n; i++) {
            //map => 3은 통나무, 4는 둬야 할 위치
            int[] input = Arrays.stream(br.readLine().replace('B', '3').replace('E', '4').split(""))
                    .mapToInt(Integer::parseInt).toArray();
            int cnt=0;
            for (int j = 0; j < input.length; j++) {
                if (input[j] == 3) {
                    sumX += j;
                    sumY += i;
                    map[i][j] = 0;
                    cnt++;//통나무 길이는 3 => 같은 횡 3개 => isV = false
                } else map[i][j] = input[j];
            }
            if(cnt==3) isV=false;
        }
        System.out.println(BFS(map, new Log(sumX / 3, sumY / 3, isV)));
    }
}
