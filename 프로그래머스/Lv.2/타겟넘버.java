class Solution {
    
    static int[] arr;
    static int t,len,answer;
    
    static void dfs(int sum, int cnt){
        if(cnt==len){
            if(sum==t) ++answer;
            return;
        } 
        dfs(sum+arr[cnt], cnt+1);//더한다
        dfs(sum-arr[cnt], cnt+1);//뺀다
    }
    public int solution(int[] numbers, int target) {
        len = numbers.length;
        arr = numbers;
        t = target;
        answer = 0;
        dfs(0,0);
        return answer;
    }
}
