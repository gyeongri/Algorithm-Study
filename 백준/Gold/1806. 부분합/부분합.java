import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		
		st= new StringTokenizer(bf.readLine());
		
		for(int i=0; i<N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int start=0, end=0;// 시작과 끝점이 첫 원소의 인덱스를 가리키게 함.
		int sum=0;
		int len=Integer.MAX_VALUE;//최솟값을 구해야하므로 초기에는 가장 큰값으로 설정
		
		while(start<=N && end<=N) {//시작점과 끝점 모두 마지막 원소에 다다를 때까지 반복함.
			if(sum>=S && len>end-start) {// S이상이 된 경우 -> 빼야함(start 증가)
				len = end-start;
				
			}
			if(sum<S) sum += arr[end++];
			else sum -= arr[start++];
		}
		
		if(len==Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(len);
	}
}