import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());//퇴사까지 남은 날
        StringTokenizer st;
        int[] T = new int[N];//날짜별 상담의 기간
        int[] P = new int[N];//날짜별 상담의 금액
        int[] memo = new int[N+1];//날짜별 받을 수 있는 최대 금액 -> 초기값 0
        for(int i=0; i<N; ++i){
            st = new StringTokenizer(bf.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<N; ++i){
            if(i+T[i]<=N){
                memo[i+T[i]] = Math.max(memo[i+T[i]],memo[i]+P[i]);
            }
            memo[i+1] = Math.max(memo[i+1], memo[i]);
        }
        System.out.println(memo[N]);
    }
}