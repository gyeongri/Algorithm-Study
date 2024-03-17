import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] v;
        List<int[]> sharks = new ArrayList<>();
        for(int i=0; i<N; ++i){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<M; ++j){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dy = {-1,-1,-1, 0,0, 1,1,1};
        int[] dx = {-1, 0, 1,-1,1,-1,0,1};
        int ny,nx,ncnt;
        int[] ts;

        int max=0;
        Queue<int[]> q = new ArrayDeque<>();

        for(int i=0; i<N; ++i){
            for(int j=0; j<M; ++j){

                if(map[i][j]==1)continue; //상어인 곳은 체크할 필요x
                v = new boolean[N][M];
                q.offer(new int[]{i,j,0});
                v[i][j] = true;
                while(!q.isEmpty()){

                    ts = q.poll();

                    for(int k=0; k<8; ++k){
                        ny = ts[0]+dy[k];
                        nx = ts[1]+dx[k];
                        ncnt = ts[2]+1;

                        if(ny<0||nx<0||ny>=N||nx>=M||v[ny][nx]) continue;
                        if(map[ny][nx]==1){//가장 가까운 아기상어를 발견함.-> 다음 for문으로 넘어가야함.
                            if(ncnt>max) max = ncnt;
                            q.clear();
                            break;
                        }

                        q.offer(new int[] {ny,nx,ncnt});
                        v[ny][nx] = true;
                    }

                }
            }
        }
        System.out.println(max);
    }
}