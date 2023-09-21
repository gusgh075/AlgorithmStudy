package baekjoon;

import java.io.*;

public class PS1013_Contact {
    boolean[] answer;

    public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] ptrn = new String[n];
        answer = new boolean[n];

        for (int i = 0; i < n; i++) {
            ptrn[i] = br.readLine();
            answer[i] = solution(ptrn[i]);
        }

        for (int i = 0; i < n; i++) {
            if (!answer[i])
                bw.write("NO\n");
            else
                bw.write("YES\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public boolean solution(String n) {
        String f, b;
        StringBuilder sb= new StringBuilder();
        while (!n.isEmpty()) {
            if (n.contains("100")) {
                f = n.substring(0, n.indexOf("100"));
                b = n.substring(n.indexOf("100")+2);

                f = f.replace("01", "");
                if (!f.isEmpty())
                    return false;

                if(b.isEmpty())
                    return false;

                if(!b.contains("01"))
                    return false;
                b=b.substring(b.indexOf("01")+2);

                if(b.length()>=2) {
                    if (b.substring(0, 2).equals("00"))
                        return false;
                }

                sb.append(b);
                while(sb.length()!=0&&sb.charAt(0)=='1'){
                    sb.deleteCharAt(0);
                }
                b=sb.toString();
                sb.delete(0,sb.length());

                if(b.isEmpty())
                    return true;
                if(b.length()==1)
                    return false;
                if(b.length()>=2){
                    if(b.substring(0,2).equals("00"))
                        n="1"+b;
                    else
                        n=b;
                }

            } else {
                n = n.replace("01", "");
                if (n.isEmpty())
                    return true;
                else
                    return false;
            }
        }
        return true;

    }
}
