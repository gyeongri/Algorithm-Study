import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        for(int t=1; t<=T; ++t){
            sb.append("#").append(t).append("\n");
            int N = Integer.parseInt(bf.readLine());//3~7
            int[][] map = new int[N][N];
            for(int i=0; i<N; ++i){
                st = new StringTokenizer(bf.readLine());
                for(int j=0; j<N; ++j){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<N; ++i){
                for(int j=0; j<N; ++j){
                    sb.append(map[N-j-1][i]);
                }
                sb.append(" ");
                for(int j=0; j<N; ++j){
                    sb.append(map[N-i-1][N-1-j]);
                }
                sb.append(" ");
                for(int j=0; j<N; ++j){
                    sb.append(map[j][N-1-i]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}