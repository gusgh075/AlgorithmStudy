package programmers;

import java.util.Arrays;

public class 양궁대회 {
    int[] info;
    public int[] solution(int n, int[] info) {
        this.info=info;

        int[] answer = new int[this.info.length];
        Arrays.fill(answer,0);

        answer=treeSearch(n,0,answer);

        if(singleScoreGap(answer)<=0){
            return new int[]{-1};
        }
        return answer;
    }
    private int[] treeSearch(int n, int index, int[] answer){
        int[] result;
        int[] answerClone=answer.clone();

        if(index==10||n==0){
            answerClone[index]=n;
            return answerClone;
        }
        if(n>this.info[index]){
            answerClone[index]=this.info[index]+1;
            int leftN=n-(this.info[index]+1);
            result=scoreGap(
                    treeSearch(leftN, index+1, answerClone),
                    treeSearch(n,index+1,answer.clone()));
        }
        else{
            result=treeSearch(n,index+1,answerClone);
        }
        return result;
    }
    private int singleScoreGap(int[] a){
        int aSum=0;
        for(int i=0;i<11;i++){
            if(this.info[i]==0&&a[i]==0){
                continue;
            }
            else if(this.info[i]>=a[i]){
                aSum-=(10-i);
            }
            else if(this.info[i]<a[i]){
                aSum+=(10-i);
            }
        }
        return aSum;
    }
    private int[] scoreGap(int[] a,int[] b){
        int aSum=singleScoreGap(a);
        int bSum=singleScoreGap(b);
        int[] result=new int[11];


        if(aSum==bSum){
            for(int i=10;i>=0;i--){
                if(a[i]>b[i]){
                    result=a.clone();
                    break;
                }
                else if(a[i]<b[i]){
                    result=b.clone();
                    break;
                }
                else{
                    result=a.clone();
                }
            }
        }
        else{
            result=aSum>bSum?a.clone():b.clone();
        }
        return result;
    }

}
