import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> noHear = new HashSet<>();
        List<String> noHearAndSee = new ArrayList<>();
        for(int i=0; i<N; ++i){
            noHear.add(bf.readLine());
        }
        String s;
        for(int i=0; i<M; ++i){
            s = bf.readLine();
            if(noHear.contains(s)){
                noHearAndSee.add(s);
            }
        }
        sb.append(noHearAndSee.size()+"\n");
        Collections.sort(noHearAndSee);
        for(String tmp: noHearAndSee){
            sb.append(tmp+"\n");
        }
        System.out.println(sb);
    }
}