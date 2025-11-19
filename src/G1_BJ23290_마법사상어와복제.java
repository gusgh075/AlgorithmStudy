import java.io.BufferedReader;
import java.io.IOException;

/*
마법 목록
1. 파이어볼
2. 토네이도
3. 파이어스톰
4. 물복사버그
5. 비바라기
6. 블리자드

크기 4 x 4 격자
시작은 (1, 1) 아랫칸 (4, 4)

물고기
- M마리
- 격자 한칸에 한마리
  - 마법사가 한칸을 차지하고 있음
- 이동방향이 있음
  - 8가지 방향 중 하나

아래 작업이 순차적으로 실행
1. 아래 5번에서 물고기가 복제되서 칸에 나타남
2. 모든 물고기가 방향대로 한칸 이동. 아래 경우 이동 불가
  - 상어가 있는칸
  - 물고기 냄새가 있는 칸
  - 격자 범위 밖
2-1. 이동불가인 칸이라면 45도 반시계 회전
2-2. 이동 못하면 이동 x
3. 상어가 3칸 이동. 상하좌우로 인접칸 이동.
3-1. 연속해서 이동하는 칸 중 격자 범위 벗어나는 칸 있으면 이동 불가한것
3-2. 물고기 있으면 격자에서 제외. 그리고 냄새를 남김
3-3. 이동방법은 가장 많은 물고기를 제외시킬 수 있는 경우
3-4. 제외할 물고기 개수가 동일한 방법이 존재한다면 사전순으로 앞서는 경우
3-5. 상(1), 좌(2), 하(3), 우(4) -> 상상좌(112) < 하우하(343) -> 상상좌(112)가 사전순으로 앞섬
4. 물고기 냄새는 2번 연습 뒤에 사라짐
5. 1에서 사용한 복제마법 완료. 복제된 물고기는 위치와 방향을 그대로 가짐.

위의 과정을 5번 반복
 */
import java.io.InputStreamReader;
import java.util.*;

public class G1_BJ23290_마법사상어와복제 {
  //fish의 dx,dy값 / 45도 반시계
  static int[] dfx = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dfy = {0, 1, 1, 1, 0, -1, -1, -1};
  //shark의 dx,dy값 / 45도 반시계
  static int[] dsx = {0, -1, 0, 1};
  static int[] dsy = {1, 0, -1, 0};
  //자료구조
  static int fn; //물고기 수
  static int mn; //상어가 마법 연습한 횟수
  static Shark shark;
  static int[][] smell = new int[4][4];
  static int[][][] curFishes = new int[4][4][8];
  static int[][][] nxtFishes = new int[4][4][8];

  //Fish 클래스
  public static class Fish {
    int x;
    int y;
    int n;

    public Fish(int x, int y, int n) {
      this.x = x;
      this.y = y;
      this.n = 1;
    }
  }

  //Shark 클래스
  public static class Shark {
    int x;
    int y;

    public Shark(int x, int y) {
      this.x = x;
      this.y = y;
    }

  }

  public static void main(String[] args) throws IOException {
    initialization();
    for (int i = 0; i < 5 + mn; i++) {
      //모든 물고기 이동
      moveFish();
      //상어 이동
      moveShark(shark);
      //물고기를 복제함
      duplicateFishes();
      //물고기 냄새 옅어짐
      reduceSmell();
    }
    long ans=0;
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        for (int d = 0; d < 8; d++) {
          ans+=curFishes[x][y][d];
        }
      }
    }
    System.out.println(ans);
  }

  public static void initialization() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    fn = input[0];
    mn = input[1];
    for (int i = 0; i < fn; i++) {
      input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      curFishes[input[0] - 1][input[1] - 1][input[2] - 1]++;
    }
    input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    shark = new Shark(input[0] - 1, input[1] - 1);
  }

  //curFish -> nxtFish에 저장, 이동방향에 따라
  public static void moveFish() {
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        for (int d = 0; d < 8; d++) {
          int nd = findFishDest(x, y, d);
          if (nd != -1) {//이동불가가 아니라면
            int nx = x + dfx[nd];
            int ny = y + dfy[nd];
            nxtFishes[nx][ny][nd] = curFishes[x][y][d];
          } else
            nxtFishes[x][y][d] = curFishes[x][y][d];
        }
      }
    }
  }

  //가능한 이동 방향을 찾음.
  //불가능 시 -1를 return;
  public static int findFishDest(int x, int y, int d) {
    for (int i = 0; i < 8; i++) {
      int nd = (d - i + 8) % 8;
      int nx = x + dfx[nd];
      int ny = y + dfy[nd];
      /*격자 밖*/
      if (!(nx < 0 || ny < 0 || nx > 3 || ny > 3
          /*냄새가 있음*/ || smell[nx][ny] != 0
          /*상어가 있음*/ || shark.x == nx && shark.y == ny)) return nd;
    }
    return -1;
  }

  public static void moveShark(Shark shark) {
    Queue<int[]> q = new ArrayDeque<>();
    for (int i = 0; i < 4; i++) {
      for (int i1 = 0; i1 < 4; i1++) {
        for (int i2 = 0; i2 < 4; i2++) {
          q.add(new int[]{i, i1, i2});
        }
      }
    }

    int[] sharkRouteX = new int[3];
    int[] sharkRouteY = new int[3];
    int maxF = -1;
    while (!q.isEmpty()) {
      int[] now = q.poll();
      int nx = shark.x;
      int ny = shark.y;
      int fn = 0;
      int[] dieFishx = new int[3];
      int[] dieFishy = new int[3];
      for (int i = 0; i < 3; i++) {
        int d = now[i];
        nx = nx + dsy[d];
        ny = ny + dsy[d];
        if (sharkCanMove(nx, ny)) {
          dieFishx[i] = nx;
          dieFishy[i] = ny;
          for (int f = 0; f < 8; f++) {
            fn += curFishes[nx][ny][f];
          }
        } else break;
      }
      if (fn > maxF) {
        sharkRouteX = dieFishx;
        sharkRouteY = dieFishy;
        maxF = fn;
      }
    }
    for (int i = 0; i < 3; i++) {
      eatFish(sharkRouteX[i],sharkRouteY[i]);
    }

  }

  //  3. 상어가 3칸 이동. 상하좌우로 인접칸 이동.
//3-1. 연속해서 이동하는 칸 중 격자 범위 벗어나는 칸 있으면 이동 불가한것
//3-2. 물고기 있으면 격자에서 제외. 그리고 냄새를 남김
//3-3. 이동방법은 가장 많은 물고기를 제외시킬 수 있는 경우
//3-4. 제외할 물고기 개수가 동일한 방법이 존재한다면 사전순으로 앞서는 경우
//3-5. 상(1), 좌(2), 하(3), 우(4) -> 상상좌(112) < 하우하(343) -> 상상좌(112)가 사전순으로 앞섬
  public static boolean sharkCanMove(int nx, int ny) {
    if(nx>=4||ny>=4||nx<0||ny<0) return false;
    return true;
  }

  private static void duplicateFishes() {
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        for (int d = 0; d < 8; d++) {
          curFishes[x][y][d]+=nxtFishes[x][y][d];
          nxtFishes[x][y][d]=0;
        }
      }
    }
  }

  private static void eatFish(int x, int y) {
    for (int d = 0; d < 8; d++) {
      curFishes[x][y][d]=0;
      nxtFishes[x][y][d]=0;
    }
    shark.x=x;
    shark.y=y;
    smell[x][y]=3;
  }

  private static void reduceSmell() {
    for (int x = 0; x < 4; x++) {
      for (int y = 0; y < 4; y++) {
        if (smell[x][y] != 0)
          smell[x][y]--;
      }
    }
  }
}
