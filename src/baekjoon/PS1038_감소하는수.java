package baekjoon;
import java.io.*;
import java.util.Stack;

public class PS1038_감소하는수 {
    //1차시도 int로는 결과값이 안담아짐 -> String으로 했어도 됐을듯
    //2차시도 결과값을 넘어섰을때 -1을 출력하게끔 했어야됨
    public void PS1038_감소하는수() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long n=Long.parseLong(br.readLine());

        bw.write(bfs(n)+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
    public Long bfs(Long n){
        Long digit = 0L;
        Stack<Long> answer = new Stack<>();
        for(Long i = 1L; i<=10; i++){
            n-=combo(10L,i);
            if(n<=0){
                digit=i;
                n+=combo(10L,i);
                break;
            }
            if(i==10){
                return -1L;
            }
        }
        if(digit==1)
            return n;
        while(digit>1) {
            digit--;
            for (Long i = digit; i < (answer.isEmpty() ? 10 : answer.peek()); i++) {
                n -= combo(i, digit);
                if (n <=0) {
                    n += combo(i, digit);
                    answer.add(i);
                    break;
                }
            }
        }
        String tmp="";
        while(!answer.isEmpty()){
            tmp=answer.pop()+tmp;
        }
        return Long.parseLong(tmp+(n-1));
    }


    public Long combo(Long set, Long sel){
        if(set==sel)
            return 1L;
        if(set==10&&sel==1)
            return 9L;
        Long result=1L;
        for(Long i=0L;i<sel;i++){
            result*=(set-i);
            result/=(i+1);
        }
        return result;
    }
}