import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int L=Integer.parseInt(st.nextToken());

        int[] arr = new int[M+1];
        for(int i=0;i<M; i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        arr[M]=L;

        for(int t=0;t<N;t++){
            int n = Integer.parseInt(br.readLine());
            int ans = 0;
            int left = 0;
            int right = arr[M];
            while(left<=right){
                int mid = (left+right)/2;
                int cnt =0;
                int prev = 0;
                for(int i=0;i<=M;i++){
                    if(arr[i]-prev >=mid){
                        cnt++;
                        prev=arr[i];
                    }
                }

                if(cnt>n){
                    left = mid + 1;
                    ans = Math.max(ans, mid);
                } else{
                    right = mid - 1;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}