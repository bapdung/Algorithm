import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int memo[]; 
    public static int N;
    public static int[] arr;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        memo = new int[N+1];
        for(int i=1; i<=N; i++) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for(int j=i; j>0; j--) {
                if(max<arr[j]) max = arr[j];
                if(min>arr[j]) min = arr[j];
                memo[i] = Math.max(memo[i], max-min + memo[j-1]); 
                //기존 i값
                //1~j-1까지 고려해 최대 잘짜여진 팀에 j부터i까지 묶어서 팀짰을때
            }
        }
//        System.out.println(Arrays.toString(memo));
        System.out.println(memo[N]);
        
    }
}