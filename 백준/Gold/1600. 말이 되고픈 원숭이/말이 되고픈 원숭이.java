import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//말의 걸음(8방향)
		int[] hdx = {-1,-2,-2,-1, 1, 2,2,1};
		int[] hdy = {-2,-1, 1, 2,-2,-1,1,2};
		
		//원숭이 걸음(상하좌우)
		int[] mdy = {-1,1, 0,0};
		int[] mdx = { 0,0,-1,1};
		
		int K,W,H;
		K = Integer.parseInt(bf.readLine());//K회 동안은 말처럼 이동 가능함.
		st = new StringTokenizer(bf.readLine());
		W = Integer.parseInt(st.nextToken());//가로
		H = Integer.parseInt(st.nextToken());//세로
		
		int[][] map = new int[H][W];

		for(int i=0; i<H; ++i) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<W; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());			
			}
		}
		//방문체크 배열
		int[][][] visited = new int[H][W][K+1];
		
		Queue<int[]> q = new ArrayDeque<>();
		//(y,x,cnt,hcnt)
		q.offer(new int[] {0,0,0,0});
		
		int[] tmp;
		int ty,tx,cnt, hcnt, ny,nx;
		while(!q.isEmpty()) {
			tmp = q.poll();
			ty = tmp[0];
			tx = tmp[1];
			cnt = tmp[2];//걸린 시간 전체
			hcnt = tmp[3];
			
			if(ty==H-1 && tx==W-1) {
				System.out.println(cnt);
				return;
			}
						
			for(int i=0; i<4; ++i) {
				ny = ty+mdy[i];
				nx = tx+mdx[i];

				if(ny>-1 && nx>-1 && ny<H && nx<W && map[ny][nx]==0 && visited[ny][nx][hcnt]==0) {//말을 hcnt번 쓴 경우에 이 위치에 왔던 적이 있는지 체크
					visited[ny][nx][hcnt]=1;
					q.offer(new int[] {ny,nx,cnt+1,hcnt});
				}		
			}
						
			if(hcnt<K) {
				for(int i=0; i<8; ++i) {
					ny = ty+hdy[i];
					nx = tx+hdx[i];
					//말을 hcnt+1번 쓴 경우에(방금 썼으니까) 이 위치에 왔던 적이 있는지 체크
					if(ny>-1 && nx>-1 && ny<H && nx<W && map[ny][nx]==0 && visited[ny][nx][hcnt+1]==0) {
						visited[ny][nx][hcnt+1]=1;
						q.offer(new int[] {ny,nx,cnt+1,hcnt+1});
					}
					
				}
			}
			
		}
		System.out.println(-1);
	}
}