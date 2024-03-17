import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(bf.readLine());
        int[] cards = new int[N];
        st = new StringTokenizer(bf.readLine());
        for(int i=0; i<N; ++i) cards[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cards);

        int M = Integer.parseInt(bf.readLine());
        int tmp;
        st = new StringTokenizer(bf.readLine());

        for(int i=0; i<M; ++i){
            tmp = Integer.parseInt(st.nextToken());
            if(Arrays.binarySearch(cards,tmp) < 0) sb.append("0 ");
            else sb.append("1 ");
        }
        System.out.println(sb);
    }
}