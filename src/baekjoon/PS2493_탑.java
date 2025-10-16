package baekjoon;

import java.io.*;
import java.util.*;

/**
 * 1.스택이 비어있지 않고 스택최상단에 위치한 타워의 높이와, 현재 타워의 높이를 비교
 * 1-1.스택에 들어있는 타워보다 현재타워의 높이가 클시, 스택의 타워 index와 높이를 저장
 * 2.스택이 비어있거나 스택최상단에 위치한 타워의 높이가 더 클시 스택에 추가 후 넘기기
 * 3.모든 타워를 탐색후 종료
 */
public class PS2493_탑{
    public static class Point{
        int index;
        int height;

        public Point(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
    public void PS2493_탑(String args[]) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int n=Integer.parseInt(br.readLine());
        ArrayList<Integer> tower=new ArrayList<>(n);
        String[] tmp=br.readLine().split(" ");
        tower.add(Integer.MAX_VALUE);
        for(int i=0;i<n;i++){
            tower.add(Integer.parseInt(tmp[i]));
        }

        Stack<Point> s=new Stack<>();
        Point p;
        for(int i=n;i>=0;i--){
            while(!s.isEmpty()&&s.peek().height<=tower.get(i)){
                p=s.pop();
                tower.set(p.index,i);
            }
            s.add(new Point(i,tower.get(i)));
        }
        for(int i=1;i<=n;i++){
            bw.write(tower.get(i)+" ");
        }
        bw.flush();
        bw.close();
        br.close();

    }

}
