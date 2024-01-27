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
        int cnt;

        public Dir(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt =cnt;//얼만큼 이동했는지
        }
        
        @Override
        public int compareTo(Dir d) {
        	//오름차순 정렬(거리가 작은 것부터 고르므로)
        	if(this.cnt < d.cnt) {//비교대상이 더 크면 -1 반환 -> 순서 그대로?
        		return -1;
        	}
        	else if(this.cnt == d.cnt) {//y도 작은거부터
        		if(this.y < d.y) return -1;
        		else if(this.y == d.y) {//y가 같다면 x가 작은거부터
        			if(this.x < d.x) return -1;
        			else if(this.x == d.x) return 0;
        			else return 1;
        		}
        		else return 1;
        	}
        	else {//비교대상이 더 작으면 1 반환 -> 순서 바꿈?
        		return 1;
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
//           boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; ++j) {
                tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;

                //아기상어의 위치 큐에 담기
                if (tmp == 9) {
                    q.offer(new Dir(i, j, 0));
                    map[i][j] = 0;
                }
            }
        }

        Dir shark;
        boolean[][] dist;//
        int ty, tx, tcnt, ny, nx, fish, size=2, ate=0, time=0;
        PriorityQueue<Dir> fishes;

        //한 위치에서 BFS 돌려서 갈 수 있는 곳 가서 물고기 먹고 큐에 담기
        while(true){
            //초기사이즈:2, 먹은 물고기양:0, 물고기 먹는 시간(1초에 한칸 이동)
            //한 위치에서 BFS 돌릴 때 방문체크하기
            dist = new boolean[N][N];
            fishes = new PriorityQueue<>();

            while(!q.isEmpty()){
                shark = q.poll();
                ty = shark.y;
                tx = shark.x;
                tcnt = shark.cnt+1;
                

                //상좌우하 로 이동
                for(int i=0; i<4; ++i){
                    ny = ty + dy[i];
                    nx = tx + dx[i];

                    //범위 밖이면 OR 이미 갔던 곳이면 넘어가기
                    if(ny<0 || nx<0 || ny>=N || nx>=N || dist[ny][nx]) continue;

                    //앞으로 가려는 칸에 있는 물고기의 크기
                    fish = map[ny][nx];

                    //자기보다 더 큰 물고기가 있으면 갈 수 없음
                    if(fish > size) continue;

                    //물고기가 없거나 같은 크기의 물고기 -> 먹을 수는 없지만 지나갈 수는 있음.
                    if(fish==0 || fish == size){
                        q.offer(new Dir(ny,nx,tcnt));//앞으로 가야할 곳에 넣는것(q)
                        dist[ny][nx] = true;//지나갔으니까 방문체크
                    }

                    //자기보다 작은 물고기 -> 먹을 수 있음. -> 리스트에 담기
                    else if(fish < size){
                    	fishes.offer(new Dir(ny,nx,tcnt));//먹을 물고기 목록에 넣는것(pq)
                    	dist[ny][nx] = true;     
                    }
                }
            }
            
            //탐색 완료했으면 가장 가까운 물고기 먹고 위치 이동하기
            //먹을 물고기가 없다면 엄마 상어를 불러야함.
            if(fishes.size()==0) {
            	System.out.println(time);
            	return;
            }
            
            shark = fishes.poll();//shark변수 재활용..
            map[shark.y][shark.x] = 0;//먹었으면 map에서 0으로 바꾸기
            
            time+=shark.cnt;//이동한 양=시간 만큼 더해주기
            
            ate++;//먹은 개수 증가
            if(ate==size){//먹은 개수가 자기 사이즈만큼 쌓이면 사이즈 1증가
                size++;
                ate = 0;
            }

            //큐가 비어서 나왔으니까 이제 물고기를 먹고 이동하기 시작할 위치를 큐에 다시 넣어주면 됨.
            q.offer(new Dir(shark.y,shark.x,0));
        }
    }
}