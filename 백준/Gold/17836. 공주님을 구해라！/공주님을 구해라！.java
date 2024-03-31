import java.io.*;
import java.util.*;

public class Main {

    static class info{
        int x, y, g, t;

        public info(int x, int y, int g, int t) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.t = t;
        }
    }

    static int N,M,T;
    static int [][]map;
    static boolean [][][]V;
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        V = new boolean[2][N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();

    }

    public static void bfs() {
        PriorityQueue<info> pq = new PriorityQueue<>(new Comparator<info>() {

            @Override
            public int compare(info o, info oo) {
                return o.t-oo.t;
            }
        });

        pq.offer(new info(0,0,0,0));
        V[0][0][0]=true;

        while(!pq.isEmpty()) {
            info temp = pq.poll();

            if(temp.x==N-1 && temp.y==M-1) {
                if(temp.t<=T) {
                    System.out.println(temp.t);
                }else System.out.println("Fail");
                return;
            }
            if(temp.t>T) break;

            for(int i=0;i<4;i++) {
                int nx = temp.x+dx[i];
                int ny = temp.y+dy[i];

                if(check(nx,ny) && !V[temp.g][nx][ny]) {
                    if(temp.g==0) {
                        if(map[nx][ny]==0) {
                            V[0][nx][ny]=true;
                            pq.offer(new info(nx,ny,temp.g,temp.t+1));
                        }
                        else if(map[nx][ny]==2) {
                            V[1][nx][ny] =true;
                            pq.offer(new info(nx,ny,1,temp.t+1));
                        }
                    }else {
                        V[1][nx][ny] = true;
                        pq.offer(new info(nx,ny,temp.g,temp.t+1));
                    }
                }
            }
        }
        System.out.println("Fail");
    }

    public static boolean check(int x, int y) {
        return x>=0 && y>=0 && x<N && y<M;
    }
}