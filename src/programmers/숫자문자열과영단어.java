package programmers;

public class 숫자문자열과영단어 {
    public  int solution(String s) {
        int answer = 0;
        String tmpAns= "";
        String[] alpha={"zero","one","two","three","four","five","six","seven","eight","nine"};


        for(int i=0; i<s.length();i++){
            for(int j=0;j<alpha.length;j++){
                if(s.length()-i>=alpha[j].length()&& alpha[j].equals(s.substring(i, alpha[j].length() + i))) {
                    tmpAns+=j;
                    break;
                }
                if(Character.isDigit(s.charAt(i))){
                    tmpAns+=s.charAt(i);
                    break;
                }
            }
        }
        answer=Integer.parseInt(tmpAns);
        return answer;
    }
}
