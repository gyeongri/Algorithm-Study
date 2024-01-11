import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int[] dy = {-1,1, 0,0};
		int[] dx = { 0,0,-1,1};
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N,M,K;
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());//세로
		M = Integer.parseInt(st.nextToken());//가로
		K = Integer.parseInt(st.nextToken());//쓰레기 개수
		
		int[][] map = new int[N][M];
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> l = new ArrayList<>(); 
		
		int r, c;
		int[] tmp;
		int tx,ty, xx,yy;
		int max=0, tmax;	
		
		for(int i=0; i<K; ++i) {
			st = new StringTokenizer(bf.readLine());
			r = Integer.parseInt(st.nextToken())-1;//r:행(세로)
			c = Integer.parseInt(st.nextToken())-1;//c:열(가로)
			map[r][c] = 1;
			l.add(new int[] {r,c});
		}
	
		boolean[][] v = new boolean[N][M];
		for(int[] t: l) {
			tmax = 0;
			q.offer(t);
			
			while(!q.isEmpty()) {
				tmp = q.poll();
				ty = tmp[0]; 
				tx = tmp[1]; 				
				
				for(int i=0; i<4; ++i) {
					yy = ty+dy[i];
					xx = tx+dx[i];
					if(yy>-1 && yy<N && xx>-1 && xx<M && map[yy][xx]==1 && !v[yy][xx]) {
						q.offer(new int[]{yy,xx});
						v[yy][xx] = true;
						++tmax;
					}
				}
			}
			if(tmax>max) {max = tmax;}
		}	
		System.out.println(max);
	}
}