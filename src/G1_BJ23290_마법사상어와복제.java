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
import java.util.*;

public class G1_BJ23290_마법사상어와복제 {
    //fish의 dx,dy값 / 45도 반시계
    static int[] dfx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dfy = {1, 1, 0, -1, -1, -1, 0, 1};
    //shark의 dx,dy값 / 45도 반시계
    static int[] dsx = {0, -1, 0, 1};
    static int[] dsy = {1, 0, -1, 0};
    //자료구조
    static int[][] smell = new int[4][4];
    static List<Fish>[][] curFishes = new ArrayList[4][4];
    static List<Fish>[][] nxtFishes = new ArrayList[4][4];
    //Fish 클래스
    public static class Fish {
        int x;
        int y;
        int d;

        public Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        public void rotate() {
            d = (d + 9) % 8;
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
        Shark shark = new Shark(0,0);
        for (int x = 0; x < curFishes.length; x++) {
            for (int y = 0; y < curFishes[x].length; y++) {
                curFishes[x][y]=new ArrayList<>();
                nxtFishes[x][y]=new ArrayList<>();
            }
        }

        for (int i = 0; i < 5; i++) {
            //모든 물고기 이동
            for(int x=0;x<4;x++){
                for(int y=0;y<4;y++){
                    for (Fish fish : curFishes[x][y]) {
                        moveFish(fish);
                    }
                }
            }
            //상어 이동
            moveShark(shark);
            //물고기를 복제함
            duplicateFishes();
            //물고기 냄새 옅어짐
            reduceSmell();
        }
    }
    public static void moveFish(Fish fish){

    }
    public static void moveShark(Shark shark){
        eatFish(1,1);
        smell[1][1]=3;
    }

    private static void duplicateFishes() {

    }

    private static void eatFish(int x, int y) {
        smell[x][y]=3;
    }

    private static void reduceSmell(){
        for (int x = 0; x < 4; x++) {
            for(int y=0;y<4;y++){
                if(smell[x][y]!=0)
                    smell[x][y]--;
            }
        }
    }
}
