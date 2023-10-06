package simulation;

import java.awt.*;
import java.io.*;
import java.util.Stack;

public class G5_BJ14891_톱니바퀴 {
    static int[][] lrindex = new int[4][2];
    static int[][] topni = new int[4][8];
    static boolean[] visit = new boolean[4];

    public static void sol() throws IOException {
        //G5_14891_톱니바퀴
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            String[] split = line.split("");
            for (int j = 0; j < 8; j++) {
                topni[i][j] = Integer.parseInt(split[j]);
            }
        }
        for (int i = 0; i < 4; i++) {
            lrindex[i][0] = 6;
            lrindex[i][1] = 2;
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            int[] bang = new int[4];
            Stack<Point> pointStack = new Stack<>();
            String[] info = br.readLine().split(" ");
            int cur = Integer.parseInt(info[0]) - 1;
            int dis = Integer.parseInt(info[1]);
            bang[cur] = dis;
            for (int j = cur; j < 3; j++) {
                if (getLeft(j+1)!= getRight(j)) {
                    bang[j + 1] = bang[j] * -1;
                } else {
                    break;
                }
            }
            for (int j = cur; j > 0; j--) {
                if (getLeft(j) != getRight(j-1)) {
                    bang[j - 1] = bang[j] * -1;
                } else {
                    break;
                }
            }
            for (int j = 0; j < 4; j++) {
                if (bang[j] == -1) {
                    turnLeft(j);
                }
                if (bang[j] == 1) {
                    turnRight(j);
                }
            }
        }
        int ans=0;
        for(int i=0;i<4;i++){
            ans=ans+(getTop(i)==0?0:(int)Math.pow(2,i));
        }
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }
    public static int getLeft(int index){
        return topni[index][(lrindex[index][0])];
    }
    public static int getRight(int index){
        return topni[index][(lrindex[index][1])];
    }
    public static int getTop(int index){
        return topni[index][(lrindex[index][0]+2)%8];
    }
    public static void turnRight(int index){
        lrindex[index][0] = (lrindex[index][0] + 1) % 8;
        lrindex[index][1] = (lrindex[index][1] + 1) % 8;
    }
    public static void turnLeft(int index){
        lrindex[index][0] = (lrindex[index][0] + 7) % 8;
        lrindex[index][1] = (lrindex[index][1] + 7) % 8;
    }
}
