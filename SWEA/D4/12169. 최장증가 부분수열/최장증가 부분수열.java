import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(bf.readLine());//테스트 케이스 개수
        int n;
        int[] arr, memo;
        for(int t=1; t<=T; ++t){
            n = Integer.parseInt(bf.readLine());//수열의 길이
            st = new StringTokenizer(bf.readLine());//수열이 공백을 기준으로 주어짐
            arr = new int[n];
            memo = new int[n];
            for(int i=0; i<n; ++i){
                arr[i] = Integer.parseInt(st.nextToken());
                memo[i] = 1;
            }

            for(int i=0; i<n; ++i){//수열을 순회하고
                for(int j=0; j<i; ++j){//해당 원소까지 첫 원소부터 순회하면서
                    if(arr[i]>arr[j]){ //해당 원소보다 더 작은게 나오는지 확인=> 증가하는 패턴을 구하는 것이므로..
                        memo[i] = Integer.max(memo[i], memo[j]+1);//max로 가장 값이 커지는 조합을 구한다.
                    }
                }
            }

            int ans = Integer.MIN_VALUE;
            for(int i=0; i<n; ++i){
                if(memo[i]>ans) ans = memo[i];
            }
            System.out.printf("#%d %d\n",t,ans);
        }

    }
}