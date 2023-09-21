package baekjoon;

import java.util.List;
import java.util.Scanner;

public class PS1025_제곱수찾기 {
         int n;//행 y
         int m;//열 x
         int answer=-1;
         int[][] table;//int[x][y]
         final List<Integer> notPow=List.of(2,3,7,8);


        public  void PS1025_제곱수찾기(){
            input();

            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    for(int k=-m;k<m;k++){
                        for(int l=-n;l<n;l++){
                            solution(new int[]{i,j},new int[]{k,l},0);
                        }
                    }
                }
            }
            System.out.println(answer);
        }

        private  void solution(int[] pos,int[] nextPos, int num){
            //현재위치가 테이블 밖에 있을시
            if(pos[0]<0||pos[0]>=m||pos[1]<0||pos[1]>=n){
                return;
            }
            //이동거리가 0일시
            if(nextPos[0]==0&&nextPos[1]==0){
                num+=table[pos[0]][pos[1]];
                checkMax(num);
            }
            //이동중일시
            else{
                checkMax(Integer.parseInt(num+""+table[pos[0]][pos[1]]));
                solution(new int[]{pos[0] + nextPos[0], pos[1] + nextPos[1]}, nextPos,
                        Integer.parseInt(num+""+table[pos[0]][pos[1]]));
            }
        }
        private  void input(){
            Scanner scanner=new Scanner(System.in);
            n=scanner.nextInt();
            m=scanner.nextInt();
            table=new int[m][n];
            for(int i=0;i<n;i++){
                String line=scanner.next();
                for(int j=0;j<m;j++) {
                    table[j][i]=Integer.parseInt(line.charAt(j) + "");
                }
            }
        }
        private  void checkMax(int num){
            if(notPow.contains(num%10)){
                return;
            }
            else if(Math.sqrt(num)%1==0.0){
                answer=Math.max(num,answer);
            }
        }
        
}
