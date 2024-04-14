import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        int[] dy = {0,1,0,-1};//우, 하, 좌, 상
        int[] dx = {1,0,-1,0};

        for(int t=1; t<=T; ++t){
            sb.append("#").append(t).append("\n");
            int N = Integer.parseInt(bf.readLine());
            int[][] map = new int[N][N];

            // N*N까지 cnt하기
            int cnt=0, dir=0, y=0, x=0, ny, nx;
            while(cnt++<N*N){
                map[y][x] = cnt;

                ny = y+dy[dir];
                nx = x+dx[dir];
                if(ny>=N || nx>=N || ny<0 || nx<0 || map[ny][nx]!=0){
                    dir = (dir+1)%4;// 범위를 벗어나거나 숫자가 있는 칸을 만나면 방향전환
                }

                y = y+dy[dir];
                x = x+dx[dir];

            }

            for(int i=0; i<N; ++i){
                for(int j=0; j<N; ++j){
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}