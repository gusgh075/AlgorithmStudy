package programmers;

class Solution {
    long ans;
    public long[] solution(long[] numbers) {
        long[] answer =new long[numbers.length];
        String strNum;
        StringBuilder sb;
        for(int i=0;i<numbers.length;i++){
            if(numbers[i]%2==1) {
                strNum="0"+Long.toBinaryString(numbers[i]);
                sb=new StringBuilder(strNum);
                int index=sb.lastIndexOf("0");
                sb.replace(index,index+2,"10");
                ans=convertolong(sb.toString());
            }
            else
                ans=numbers[i]+1;
            answer[i]=ans;
        }
        return answer;
    }

    public long convertolong(String num){
        long answer = 0;
        int len = num.length();
        for (int i = len - 1; i >= 0; i--) {
            char digit = num.charAt(i);
            if (digit == '1') {
                answer += (long) Math.pow(2, len - 1 - i);
            }
        }
        return answer;
    }
}