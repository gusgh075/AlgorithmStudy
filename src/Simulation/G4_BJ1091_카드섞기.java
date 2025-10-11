package Simulation;

import java.util.Scanner;

//25-10-11-9:50 시작 10:16 종료 26분 소요
/*
3명의 플레이어(0,1,2) -> N개의 카드 이용(0~N-1)
카드를 섞은 뒤
0부터 N-1위치에 있는 카드를 플레이어한테 순서대로 나눠줌
지민 -> 사기꾼

N   3       카드수
P   2 0 1   0번째 카드는 2번째 플레이어, 1번째 카드는 1번째 플레이어...
S   1 2 0   카드섞는법 => 한번 카드를 섞으면 0번째는 1번째로, 1번째는 2번째로, 2번째는 0번째로 가야됨

 */
public class G4_BJ1091_카드섞기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] p = new int[n];   //카드 배열
        int[] s = new int[n];   //바꾸는 방법
        for (int i = 0; i < p.length; i++) {
            p[i] = sc.nextInt();
        }
        for (int i = 0; i < s.length; i++) {
            s[i] = sc.nextInt();
        }
        int[] p_origin=p.clone();   //만일 p_origin과 p가 같아지면 -1출력
        int ans=0;
        while(!check_end(p)){
            swap(p,s);
            ans++;
            if(check_inf(p_origin,p)) {
                ans = -1;
                break;
            }
        }
        System.out.println(ans);

    }
    public static void swap(int[] p,int[] s){
        int[] clone = p.clone();
        for (int i = 0; i < s.length; i++) {
            p[s[i]]=clone[i];
        }
    }
    public static boolean check_end(int[] p){
        for (int i = 0; i < p.length; i++) {
            if(p[i]!=i%3)
                return false;
        }
        return true;
    }
    public static boolean check_inf(int[] p_origin,int[] p){
        for (int i = 0; i < p_origin.length; i++) {
            if(p[i]!=p_origin[i])
                return false;
        }
        return true;
    }
}
