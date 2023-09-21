package programmers;

import java.util.ArrayList;
import java.util.Arrays;

public class solving_후보키 {
    String[][] table;
    int n;//x
    int m;//y
    public int solution(String[][] relation) {

        int answer = 0;
        int m=relation.length;
        int n=relation[0].length;
        table=new String[n][m];
        for (int j=0;j<m;j++) {
            for(int i=0;i<n;i++){
                table[i][j]=relation[j][i];
            }
        }
        Arrays.binarySearch(table[0],table[0][1]);


        return answer;
    }
    public void checkTuple(int x,int[] index){
        ArrayList<String[]> tuples=new ArrayList<>();
        for(int i=0;i<m;i++){

        }

    }
}
