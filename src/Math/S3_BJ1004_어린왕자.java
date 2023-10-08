package Math;

import java.awt.*;
import java.io.*;

/**
 * 일단 처음에 과연 진입, 아웃을 어떻게 카운트 해야될까 고민했는데,
 *
 * 어린왕자 우주선이 곡선을 꼭 그려야 하는가?NO
 *
 * 하여, 그냥 원에 속하는지 안속하는지를 기준으로 알고리즘을 짰다
 *
 * 근데 이런 수학에 손뗀 내가 거리공식을 잘못해서 5분정도 지체된것같다
 *
 * 그래도 이번 풀이는 나름 막힘없이 잘 해낸것같아 기분은 좋았다
 */
public class S3_BJ1004_어린왕자 {
    static int n;
    static Point start = new Point();
    static Point end = new Point();
    static int[][] planet;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            ans = 0;
            String[] split = br.readLine().split(" ");
            start.x = Integer.parseInt(split[0]);
            start.y = Integer.parseInt(split[1]);
            end.x = Integer.parseInt(split[2]);
            end.y = Integer.parseInt(split[3]);
            int k = Integer.parseInt(br.readLine());
            planet = new int[k][3];
            for (int j = 0; j < k; j++) {
                String[] s = br.readLine().split(" ");
                for (int l = 0; l < 3; l++) {
                    planet[j][l] = Integer.parseInt(s[l]);
                }
            }
            for (int j = 0; j < k; j++) {
                //행성과 시작점 도착점의 거리
                double sdist = dist(start.x, start.y, planet[j][0], planet[j][1]);
                double edist = dist(end.x, end.y, planet[j][0], planet[j][1]);
                //같은원에속할때
                if (sdist < planet[j][2] && edist < planet[j][2]) {
                    continue;
                } else if (sdist < planet[j][2] || edist < planet[j][2]) {
                    ans++;
                }
            }
            bw.write(ans + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
