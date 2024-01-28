import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visited;
	static int N;
	static int minDiff = Integer.MAX_VALUE;
	static List[] areaList;
	static int[] population;
	
	/*A그룹, B그룹 모두 연결되어 있다면 true 반환*/
	public static boolean bfs(List<Integer> group) {
		
		Queue<Integer> q = new ArrayDeque<>();
//		System.out.println(listA.get(0));

		int groupSize = group.size();
		boolean[] v = new boolean[N+1];
		int start = (int) group.get(0);
		q.offer(start);
		v[start] = true;
		int tmp, cnt=1;
		while(!q.isEmpty()) {
			tmp = q.poll();
			for(Object i : areaList[tmp]) {
				int num = (int)i;
				if(group.contains(num) && !v[num]) {//뽑은 그룹에 속한 원소이면 큐에 넣고 아니면 말기+아직 안간 원소만 넣기
					q.offer(num);
					v[num] = true;
					++cnt;
				}
			}
		}
		if(cnt==groupSize)return true;
		return false;
	}
	
	
	
	public static void subset(int cnt) {
		if(cnt == N) {//N개 모두 골랐다면..
			//서로 이어져 있는지 확인하기(전체를 BFS로 던지는 방법 vs 두 구역으로 나눠서 던지는 방법)
			List<Integer> listA = new ArrayList<>();
			List<Integer> listB = new ArrayList<>();
			for(int i=1; i<=N; ++i) {
				if(visited[i]) listA.add(i);
				else listB.add(i);
			}

			
			int lenA = listA.size(), lenB = listB.size();
			int sumA=0, sumB=0;
			//한쪽으로 모두 몰린 경우는 제외하고 BFS 로 보냄.
			if(lenA==0 || lenB==0) return;
		
			//A,B 그룹 모두 연결된 경우, population 값을 계산해서 최솟값 갱신여부 확인
			if(bfs(listA) && bfs(listB)) {
				for(int i=0; i<lenA; ++i) {
					sumA += population[listA.get(i)];
				}
				for(int i=0; i<lenB; ++i) {
					sumB += population[listB.get(i)];
				}
				minDiff = Math.min(minDiff, Math.abs(sumA-sumB));
			}
			return;
		}
		//선택하고 다음턴
		visited[cnt] = true;
		subset(cnt+1);
		//선택 안하고 다음턴
		visited[cnt] = false;
		subset(cnt+1);
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(bf.readLine());//N: 구역의 개수
        
        //0~(N-1) 번 구역의 인구수 배열
        population = new int[N+1];
        st = new StringTokenizer(bf.readLine());
        for(int i=1; i<N+1; ++i) {
        	population[i] = Integer.parseInt(st.nextToken());
        }
        
        //인접 리스트 배열 areaList
        areaList = new ArrayList[N+1];
        
        for(int i=1; i<=N; ++i) {
        	areaList[i] = new ArrayList<>();
        }
        
        int n;
        //각 구역별 인접 구역의 정보
        for(int i=1; i<=N; ++i) {
        	st = new StringTokenizer(bf.readLine());
        	n = Integer.parseInt(st.nextToken());//i번째 구역과 인접한 구역의 수
//        	System.out.println(i+"번째구역에는 "+n+"개의 구역이 있음.");
        	for(int j=0; j<n; ++j) {
        		areaList[i].add(Integer.parseInt(st.nextToken()));
        	}
        }
    
        //두 구역으로 나누기 - 부분집합 이용
        visited = new boolean[N+1];
        subset(1);
        
        if(minDiff==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minDiff);
	}
}