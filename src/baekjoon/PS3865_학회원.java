package baekjoon;

import java.io.*;
import java.util.*;

public class PS3865_학회원 {

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = 1;
        String input;
        String firstK = "";
        while (true) {
            HashMap<String, ArrayList<String>> member = new HashMap<>();
            //입력
            n = Integer.parseInt(br.readLine());
            if (n == 0)
                break;
            for (int i = 0; i < n; i++) {
                input = br.readLine();
                int index = input.indexOf(":");
                if (i == 0)
                    firstK = input.substring(0, index);
                input = input.substring(0, input.length() - 1);
                member.put(input.substring(0, index),
                        new ArrayList<>(Arrays.asList(input.substring(index + 1).split(","))));
            }
            ArrayList<String> visit = new ArrayList<>();
            ArrayList<String> answer = new ArrayList<>();
            while(!member.get(firstK).isEmpty()){
                ArrayList<String> clone = new ArrayList<>(member.get(firstK));
                for(int i = 0; i< member.get(firstK).size(); i++){
                    String cur = member.get(firstK).get(i);
                    if(member.containsKey(cur)){
                        //키 계산했다면
                        if(visit.contains(cur)){
                            clone.remove(cur);
                        }
                        //키 계산 안했다면
                        else{
                            clone.remove(cur);
                            visit.add(cur);
                            clone.addAll(member.get(cur));
                        }
                    }
                    else{
                        //밸류 넣었었다면
                        if(answer.contains(cur)){
                            clone.remove(cur);
                        }
                        //밸류 안넣었었다면
                        else{
                            answer.add(cur);
                            clone.remove(cur);
                        }
                    }
                }
                member.put(firstK,clone);
            }
            bw.write(answer.size()+"\n");
        }
        bw.close();
        br.close();

    }

}