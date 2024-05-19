import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(bf.readLine()," ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][M+1];
        int[][] dp = new int[N+1][M+1];

        for(int i=1; i<=N; ++i){
            st = new StringTokenizer(bf.readLine());
            for(int j=1; j<=M; ++j){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<dp.length;i++) {
            for(int j=1;j<dp[0].length;j++) {
                dp[i][j]=Math.max(dp[i-1][j],Math.max(dp[i-1][j-1],dp[i][j-1]))+map[i][j];
            }
        }
        System.out.println(dp[N][M]);

    }
}