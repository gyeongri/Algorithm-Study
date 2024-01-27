import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> list = new ArrayList<>();
    static List<Integer>[] adjList;
    static int min = Integer.MAX_VALUE;
    
    static void dfs(int N, int cnt, boolean[] visited) {
        if(cnt == N+1) {
            boolean[] visited2 = new boolean[N+1];
            int res;
            res = toDo(N, visited, visited2);
            if(res<min) min = res;
            return;
        }
        visited[cnt] = true;
        dfs(N, cnt+1, visited);
        visited[cnt] = false;        
        dfs(N, cnt+1, visited);        
    }
    
    static int toDo(int N, boolean[] combi, boolean[] v) {
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        
        for(int i=1; i<=N; ++i) {
            if(combi[i] == true) {
                aList.add(i);
            }
            else bList.add(i);
        }
        Queue<Integer> q = new ArrayDeque<>();
        int tmp, flag = 0;
        if(aList.isEmpty() || bList.isEmpty()) {
           return Integer.MAX_VALUE;
        }
        int ab[] = new int[] {aList.get(0), bList.get(0)};
        List<List<Integer>> abList = new ArrayList<>();
        abList.add(aList);
        abList.add(bList);
        for(int i = 0; i < 2; ++i) {
           q.offer(ab[i]);
            v[ab[i]] = true;
            while(!q.isEmpty()) {
               flag = 0;
                tmp = q.poll();
                
               for(int j:adjList[tmp]) {
                  if(v[j] == false && abList.get(i).contains(j)) {
                     q.offer(j);
                     v[j] = true;
                  }
               }
            }
        }
        for(int i:aList) {
           if(v[i] == false) {++flag;break;}
        }
        for(int i:bList) {
           if(v[i] == false) {++flag;break;}
        }
        if(flag > 0) return Integer.MAX_VALUE;
        
        int sum1=0, sum2=0;
        for(int i:aList) {
            sum1+=population[i];
        }
        for(int i:bList) {
            sum2+=population[i];
        }
        return Math.abs(sum1-sum2);
    }
    
    static int[] population;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(bf.readLine());
        population = new int[N+1];
        st = new StringTokenizer(bf.readLine());
        for(int i=1; i<=N; ++i) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        
        int n, tmp;
        adjList = new ArrayList[N+1];
        for(int i=0; i<=N; ++i) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; ++i) {
            st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            for(int j=0; j<n; ++j) {
                tmp = Integer.parseInt(st.nextToken());
                adjList[i].add(tmp);
            }
        }
        boolean[] visited = new boolean[N+1];
        
        dfs(N, 1, visited);
        if(min == Integer.MAX_VALUE) {
           System.out.println(-1);}
        else System.out.println(min);
    }
}