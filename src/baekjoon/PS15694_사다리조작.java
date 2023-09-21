package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class PS15694_사다리조작 {
     int n;
     int m;
     int h;
     int[][] line;
     int min;
     int answer=Integer.MAX_VALUE;
    public  int solution(){
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        m=scanner.nextInt();
        h=scanner.nextInt();
        min=Integer.MAX_VALUE;
        line=new int[n][h];

        for (int[] lines :
                line) {
            Arrays.fill(lines,0);
        }

        for(int i=0;i<m;i++){
            int a= scanner.nextInt()-1;
            int b=scanner.nextInt()-1;
            line[b][a]=2;
            line[b+1][a]=1;
        }

        if(check(line)){
            answer=0;
        }
        else {
            makeLine(0, 0, 0,0);
        }

        if(answer>3)
            answer=-1;
        return answer;
    }

    public  void makeLine(int x, int y, int cur, int befcur){
        //현재 사다리개수가 3이거나 정답보다 사다리개수가 클 경우 종료
        if(cur>3||cur>=answer)return;
        //사다리가 밑으로 그대로 내려간다면 정답과 최소값을 비교
        if(cur!=befcur&&check(line)){
            answer=Math.min(cur,answer);
            return;
        }
        //마지막으로 갔을 때 종료
        if(x==n-2&&y==h){
            return;
        }
        //한줄의 마지막으로 갔을 때 옆줄로 이동
        if(y==h){
            makeLine(x+1,0,cur,cur);
        }
        //아직 세로줄을 진행중일 때
        else {
            //줄을 안만들 때
            makeLine(x, y + 1, cur,cur);
            //줄을 만들 때
            if (line[x][y] == 0 && line[x+1][y]==0) {
                line[x][y] = 2;
                line[x + 1][y] = 1;
                makeLine(x, y + 1, cur + 1,cur);
                line[x][y]=0;
                line[x+1][y]=0;
            }
        }

    }
    public  boolean check(int[][] line){

        //각줄의 끝까지 가는지
        for(int i=0;i<n;i++){
            int current=i;
            for(int j=0;j<h;j++){
                switch (line[current][j]){
                    case 0:
                        break;
                    case 1:
                        current--;
                        break;
                    case 2:
                        current++;
                        break;
                }
            }
            if(i!=current){
                return false;
            }
        }
        return true;
    }
}
