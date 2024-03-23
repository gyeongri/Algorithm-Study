import java.util.*;
import java.io.*;

public class Main {

    static class Node{
        int y;
        int x;
        int val;

        Node(int y, int x, int val){
            this.y = y;
            this.x = x;
            this.val = val;
        }

    }

    public static void main(String[] args) throws IOException {
        int R,C,T;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];
        int[][] map2 = new int[R][C];
        Node[] airCleaner = new Node[2];
        List<Node> dust = new ArrayList<>();
        int tmp, cnt=0;

        //-1인지 검사하면서 저장하는 것 vs 모두 저장한 후 -1 위치를 찾는것.. 어느게 빠를까?
        for(int i=0; i<R; ++i){
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<C; ++j){
                tmp = Integer.parseInt(st.nextToken());

                if(tmp==-1){//공기청정기인 경우 따로 저장
                    airCleaner[cnt++] = new Node(i,j,-1);
                }
                else if(tmp!=0){//미세먼지가 있는 칸도 따로 저장
                    dust.add(new Node(i,j,tmp));
                }

                map[i][j] = tmp;
            }
        }

        //확산 방향
        int[] dy = {-1,1,0,0};
        int[] dx = {0,0,-1,1};

        int amount, amount2;
        int nx, ny ;
        //t초 반복
        for(int t=0; t<T; ++t){
            map2 = new int[R][C];//처음부터 다시 계산해야하므로 map2는 1초후 다시 리셋되어야 함.
            //1. 먼지확산
            for(Node n:dust){//먼지가 들어있는 칸 모두 확인
                amount = n.val; //초기 먼지량
                amount2 = (int) Math.floor(amount/5.0);//확산될 먼지량
                if(amount2==0) continue;//확산할 양만큼 없는 경우 pass

                for(int i=0; i<4; ++i){//4 방향으로 확산
                    ny = n.y + dy[i];
                    nx = n.x + dx[i];
                    //칸이 없거나 공기청정기가 있는 칸으로는 확산 x
                    if(ny<0 || nx<0 || ny>=R || nx>=C || map[ny][nx]==-1) continue;
                    map[n.y][n.x] -= amount2; //확산하는 양만큼 빼주기
                    map2[ny][nx] += amount2;

                }
            }

            //확산된 먼지양이 저장된 맵2를 원래의 맵에 합쳐주기
            for(int i=0; i<R; ++i){
                for(int j=0; j<C; ++j){
                    map[i][j] += map2[i][j];
                }
            }

            //2. 공기청정기

            //위쪽   : airCleaner[0]-> 반시계
            //아래쪽 : airCleaner[1]-> 시계

            int last1 = airCleaner[0].y-1;//y1위치-1
            int last2 = airCleaner[1].y+1;//y2위치+1

            map[last1][0] = 0;//공기청정기로 들어온 먼지 없어짐.
            map[last2][0] = 0;

            for(int i=last1; i>0; --i){//공기청정기 하나 윗칸부터 그 위의 값을 당겨오기
                map[i][0] = map[i-1][0];
            }
            for(int i=1; i<C; ++i){//가로줄 하나씩 당겨오기
                map[0][i-1] = map[0][i];
            }
            for(int i=0; i<last1+1; ++i){//
                map[i][C-1] = map[i+1][C-1];
            }
            for(int i=C-1; i>1; --i){
                map[last1+1][i] = map[last1+1][i-1];//2열에 1열을 옮기는 것까지만 함.
            }
            //공기청정기에서 바로 나온 바람은 0
            map[last1+1][1] = 0;

            for(int i=last2; i<R-1; ++i){//공기청정기 하나 아래칸부터 그 위의 값을 당겨오기
                map[i][0] = map[i+1][0];
            }
            for(int i=1; i<C; ++i){//가로줄 하나씩 당겨오기
                map[R-1][i-1] = map[R-1][i];
            }
            for(int i=R-2; i>=last2-1; --i){//
                map[i+1][C-1] = map[i][C-1];
            }
            for(int i=C-1; i>1; --i){
                map[last2-1][i] = map[last2-1][i-1];//2열에 1열을 옮기는 것까지만 함.
            }
            //공기청정기에서 바로 나온 바람은 0
            map[last2-1][1] = 0;

            //새로운 먼지 리스트 생성
            dust = new ArrayList<>();
            for(int i=0; i<R; ++i){
                for(int j=0; j<C; ++j){
                    if(map[i][j]!=-1) dust.add(new Node(i,j,map[i][j]));
                }
            }
        }

        //3. 남아있는 먼지의 양 확인하기
        int sum=0;
        for(int i=0; i<R; ++i){
            for(int j=0; j<C; ++j){
                sum += map[i][j];
            }
        }
        System.out.println(sum+2);//공기청정기 값만큼 더해준다.(-1 두개)
    }
}