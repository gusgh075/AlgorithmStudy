package BruteForce;

import java.io.*;
import java.util.Arrays;

/**
 * 하~~~ 6번틀리고 겨우맞았습니다
 * 마지막에 제가 1~n-1까지 시작점을 정하고 2~n까지 도착점으로 정해서 탐색했는데
 * 아니 이게뭐야! n번째 answer값을 비교를 안해버렸네?!
 * 아이고~~~ 그래서 결국 몇시간을 고민하다가, 반례들을 상상해서 입력해봤는데
 * 뭐지~~ 정답이 이상한게 있네!
 * 하고 찾아봤더니 결.국.은 나왔습니다!
 * 저는 말하는 감자입니다
 * 하지만 곧 움직이는 감자가 될 수 있겠죠
 * 그날까지 화이팅입니다
 * 그리고 이글을 보는 당신에겐 제가 행운을 드릴게요
 * 제 행운은 아니고, 그냥 자신의 행운이 -라면 +로 abs해드리고
 * +라면 pow로 제곱해드릴게요
 * 아 실수라면 pow하면 안되니, 음... 아! 실수면 pow(10,9) 곱해드릴게요
 * 변수명은... long으로 해드릴게요! 오버플로우나면 사고니까요
 * 그리고 정수는 pow(n,10)해드릴게요!!
 * 다들 좋은하루 보내시길 바랍니다.
 */
class G4_BJ1027_고층건물 {
    int n;
    public void PS1027_고층건물() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n=Integer.parseInt(br.readLine());
        long[] building = new long[n];
        String[] input = br.readLine().split(" ");
        for(int i=0;i<n;i++){
            building[i]=Long.parseLong(input[i]);
        }
        bw.write(solution(building)+"");

        bw.flush();
        bw.close();
        br.close();
    }
    public long solution(long[] building){
        int[] answer=new int[n];
        int ans = 0;
        Arrays.fill(answer,0);
        //i는 시작빌딩, j는 끝나는빌딩
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(j-i==1){
                    answer[j]++;
                    answer[i]++;
                    continue;
                }
                double hps= (double) (building[j] - building[i]) /(j-i);
                for(int k=i+1;k<j;k++){
                    double tmphps = (double) (building[k] - building[i]) / (k - i);
                    if(tmphps>=hps) {
                        break;
                    }
                    if(k==j-1) {
                        answer[j]++;
                        answer[i]++;
                    }
                }
            }
            ans=Math.max(answer[i],ans);
        }
        return ans;

    }
}
