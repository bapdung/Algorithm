import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    public static int N, K;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            arr = new int[N];
            for(int i=0; i<N; i++){
                int n = Integer.parseInt(st.nextToken());
                arr[i] = n;
            }
            Arrays.sort(arr);
            int rst = twoPointer();
            sb.append(rst).append("\n");
        }
        System.out.println(sb);
    }
    public static int twoPointer(){
        int s = 0;
        int e = N-1;
        int minGap = Integer.MAX_VALUE;
        int answer = 0;
        while(s<e){
            int sum = arr[s] + arr[e];
            int gap = Math.abs(sum - K);
            if(gap <= minGap){
                if(minGap > gap) answer = 0;
                minGap = gap;
                answer++;
            }
            if(sum >= K){
                e--;
            } else{
                s++;
            }
        }
        return answer;
    }
}