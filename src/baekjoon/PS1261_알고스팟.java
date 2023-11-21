package baekjoon;
import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 여기서 내가 헷갈린 부분은
 * visited[pos.x][pos.y] = true;
 * visited[dx][dy]=true 의 위치이다.
 * 첫번째 visited는 4방향 탐색 전에 넣었고
 * 두번째 visited는 4방향 탐색 안에 넣었다.
 * 먼저 queue에는 벽을 적게부순 순서로 넣어, 다음번 poll할때는 가장 적게 벽을 부순 좌표값으로 설정된다.
 * 하여, 어차피 4방향 탐색할때 해당 4방향좌표에는 최소 벽부순값이 들어가게된다.
 * 만일 x,y가 3,4일때 첫번째 visited방식이라면 (3,3),(3,5),(2,4),(4,4)에서 탐색할필요가 없다.(물론 운이 안좋을때의 경우지만)
 *
 * 참조)
 * 첫번째 visited에는 queue개수가 26개까지 들어갔으나,
 * 두번째 visited에는 queue개수가 13개까지 들어갔으므로, 절반의 메모리만을 가져간다.
 *
 */
public class PS1261_알고스팟 {
     int map[][];
     int m;//가로
     int n;//세로
    public  void PS1261_알고스팟() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());


        m=Integer.parseInt(st.nextToken());//가로
        n=Integer.parseInt(st.nextToken());//세로

        map=new int[m+1][n+1];

        for(int i=1;i<=n;i++){
            String input=br.readLine();
            for(int j=1;j<=m;j++){
                map[j][i]=Character.getNumericValue(input.charAt(j-1));
            }
        }

        int ans=bfs();
        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();

    }
    public  int bfs(){
        PriorityQueue<Node> queue=new PriorityQueue<>();
        boolean[][] visited =new boolean[m+1][n+1];
        int[] distx={1,0,-1,0};
        int[] disty={0,1,0,-1};
        queue.offer(new Node(1,1,0));
        visited[1][1]=true;

        int dx,dy;
        while(!queue.isEmpty()){
            System.out.printf("queue : %d\n",queue.size());
            Node pos=queue.poll();
//            visited[pos.x][pos.y] = true;
            if(pos.x==m&&pos.y==n){
                return pos.breakWall;
            }
            for(int i=0; i<4;i++) {
                dx=distx[i]+pos.x;
                dy=disty[i]+pos.y;
                if(dx>m||dy>n||dx==0||dy==0)
                    continue;
                if(visited[dx][dy])
                    continue;
                if (map[dx][dy] == 0) {
                    visited[dx][dy] = true;
                    queue.offer(new Node(dx,dy,pos.breakWall));
                }
                else {
                    visited[dx][dy] = true;
                    queue.offer(new Node(dx,dy,pos.breakWall+1));
                }
            }
        }

        return 0;
    }
    class Node implements Comparable<Node>{
        public int x;
        public int y;
        public int breakWall;

        public Node(int x, int y, int breakWall) {
            this.x = x;
            this.y = y;
            this.breakWall = breakWall;
        }

        @Override
        public int compareTo(Node o) {
            return this.breakWall-o.breakWall;
        }
    }
}