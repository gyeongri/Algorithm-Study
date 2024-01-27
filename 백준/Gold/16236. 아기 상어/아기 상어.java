import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static class Dir implements Comparable<Dir>{
        int y;
        int x;

        public Dir(int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        @Override
        public int compareTo(Dir d) {
        	if(this.y < d.y) return -1;
        	else if(this.y > d.y) return 1;
        	else {
        		if(this.x < d.x) return -1;
        		else if (this.x > d.x) return 1;
        		else return 0;
        	}
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] dy = {-1, 0, 1, 0};//상,좌,우,하
        int[] dx = {0, -1, 0, 1};
        int N, tmp;
        Queue<Dir> q = new ArrayDeque<>();

        N = Integer.parseInt(bf.readLine());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; ++j) {
                tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;

                //아기상어의 위치 큐에 담기
                if (tmp == 9) {
                    q.offer(new Dir(i, j));
                    map[i][j] = 0;
                }
            }
        }

        Dir shark, tfish;
        int[][] dist;// 여기다가 걸리는 거리 담기
      //초기사이즈:2, 먹은 물고기양:0, 물고기 먹는 시간(1초에 한칸 이동)
        int ty, tx, tcnt, ny, nx, fish, size=2, ate=0, time=0;
        PriorityQueue<Dir> fishes;

        //한 위치에서 BFS 돌려서 갈 수 있는 곳 가서 물고기 먹고 큐에 담기
        while(true){

            //한 위치에서 BFS 돌릴 때 방문체크하기
            dist = new int[N][N];
            fishes = new PriorityQueue<>();

            while(!q.isEmpty()){
                shark = q.poll();
                ty = shark.y;
                tx = shark.x;

                //상좌우하 로 이동
                for(int i=0; i<4; ++i){
                    ny = ty + dy[i];
                    nx = tx + dx[i];

                    //범위 밖이면 OR 이미 갔던 곳이면 넘어가기
                    if(ny<0 || nx<0 || ny>=N || nx>=N || dist[ny][nx]!=0) continue;

                    //앞으로 가려는 칸에 있는 물고기의 크기
                    fish = map[ny][nx];

                    //자기보다 더 큰 물고기가 있으면 갈 수 없음
                    if(fish > size) continue;

                    //이제 나올 조건들은 무조건 방문체크 한번씩은 해야함.
                    dist[ny][nx] = dist[ty][tx]+1;//지나갔으니까 방문체크
                    //물고기가 없거나 같은 크기의 물고기 -> 먹을 수는 없지만 지나갈 수는 있음.
                    if(fish==0 || fish == size){
                        q.offer(new Dir(ny,nx));//앞으로 가야할 곳에 넣는것(q)
                    }

                    //자기보다 작은 물고기 -> 먹을 수 있음. -> 리스트에 담기
                    else if(fish < size){
                    	if(fishes.size()!=0) {
                    		tfish = fishes.peek();
                    		if(dist[tfish.y][tfish.x] >= dist[ny][nx])//이전에 담은 물고기보다 거리가 같거나 작을 때만 담는다.
                    			fishes.offer(new Dir(ny,nx));//먹을 물고기 목록에 넣음(pq)
                    	}
                    	else {
                    		fishes.offer(new Dir(ny,nx));//먹을 물고기 목록에 넣음(pq)
                    	}
                    }
                    
                }

            }
            
            //먹을 물고기가 없다면 엄마 상어를 불러야함.
            if(fishes.size()==0) {
            	System.out.println(time);
            	return;
            }           
            shark = fishes.poll();//shark변수 재활용..
            map[shark.y][shark.x] = 0;//먹었으면 map에서 0으로 바꾸기
            time+=dist[shark.y][shark.x];//이동한 양=시간 만큼 더해주기
            
            ate++;//먹은 개수 증가
            if(ate==size){//먹은 개수가 자기 사이즈만큼 쌓이면 사이즈 1증가
                size++;
                ate = 0;
            }       
            //큐가 비어서 나왔으니까 이제 물고기를 먹고 이동하기 시작할 위치를 큐에 다시 넣어주면 됨.
            q.offer(new Dir(shark.y,shark.x));  
        }
    }
}