import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    public static int N;
    public static int K;
    public static int[] arr;
    public static int[] diff;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        diff = new int[N-1];
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(i != 0){
                diff[i-1] = arr[i] - arr[i-1];
                sum += diff[i-1];
            }
        }
        Arrays.sort(diff);
        for(int i = N-2 ;  i>N-2-(K-1) ; i--){
            sum -= diff[i];
        }
        System.out.println(sum);
    }
}