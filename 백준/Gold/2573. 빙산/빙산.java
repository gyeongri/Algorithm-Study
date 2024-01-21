import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    static void dfs(int R, int C, int x, int y, int[][] visited, int[] dx, int[]dy, int[][] map, int[][] melt) {
        visited[x][y] = 1;
 
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if(0 <= nx && nx < R && 0 <= ny && ny < C) {
                // 1년 후에 녹는 빙산의 양
                if(map[nx][ny] == 0)
                    melt[x][y]++;

                if(visited[nx][ny] == 0 && map[nx][ny] != 0)
                    dfs(R, C, nx, ny, visited, dx, dy,map, melt);                    
            }
        }
    }
    
    static void func_melt(int R, int C, int[][] map, int[][] melt, int[][] visited) {    
        
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                map[i][j] -= melt[i][j];
 
                if(map[i][j] < 0)
                    map[i][j] = 0;
                                    
                visited[i][j] = 0;
                melt[i][j] = 0;
            }
        }
    }
	
    static void sol(int[] dx, int[] dy, int R, int C, int[][] map, int[][] melt, int[][] visited) {
        //DFS
        int year = 0;
        
        while(true) {
            int cnt = 0;
            for(int i=0; i<R; i++) {
                for(int j=0; j<C; j++) {
                    if(visited[i][j] == 0 && map[i][j] != 0) {
                        dfs(R, C, i, j, visited, dx, dy,map, melt);
                        cnt++;
                    }
                }
            }
 
            if(cnt == 0) {
                System.out.println(0);
                break;
            } else if(cnt >= 2) {
                System.out.println(year);
                break;
            }
 
            func_melt(R, C, map,melt, visited);
            year++;
        }
    }

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(bf.readLine());
		int R = Integer.parseInt(st.nextToken());//세로
		int C = Integer.parseInt(st.nextToken());//가로

		//몇년 째에 두 덩어리 이상이 되는지 알아야함.
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		int[][] map = new int[R][C];
        int[][] visited = new int[R][C];
        int[][] melt = new int[R][C];
        
        for(int a=0; a<R; a++) {
            st = new StringTokenizer(bf.readLine());
            for(int b=0; b<C; b++) {
                map[a][b] = Integer.parseInt(st.nextToken());
            }      

        }
        sol(dx, dy, R,C, map, melt, visited);
		
	}

}