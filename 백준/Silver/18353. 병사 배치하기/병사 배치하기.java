import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N+1];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for(int i=0; i<N;++i){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        int max=0;
        for(int i=0; i<N; ++i){//현재
            for(int j=0; j<=i; ++j){//비교대상
                if(arr[i]<arr[j]) dp[i] = Math.max(dp[i],dp[j]+1);
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(N-max);
    }
}