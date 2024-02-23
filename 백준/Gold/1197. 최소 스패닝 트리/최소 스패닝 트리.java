import java.util.*;
import java.io.*;

public class Main {
    static class node implements Comparable<node>{
        int to;
        int cost;

        public node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(node N){
            if(this.cost > N.cost) return 1;
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        List<node>[] nodeList = new ArrayList[V+1];

        for(int i=1; i<=V; ++i){
            nodeList[i] = new ArrayList<node>();
        }

        int from,to,cost;
        for(int i=1; i<=E; ++i){
            st = new StringTokenizer(bf.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            nodeList[from].add(new node(to,cost));
            nodeList[to].add(new node(from,cost));
        }

        boolean[] visited = new boolean[V+1];
        Queue<node> pq = new PriorityQueue<>();
        pq.offer(new node(1,0));

        node tmp;
        int sum=0;
        while(!pq.isEmpty()){
            tmp = pq.poll();
            to = tmp.to;
            cost = tmp.cost;

            if(visited[to]) continue;
            else{
                visited[to] = true;
                sum += cost;
                for(node N : nodeList[to]){
                    if(!visited[N.to]) pq.offer(new node(N.to,N.cost));
                }
            }
        }
        System.out.println(sum);
    }
}