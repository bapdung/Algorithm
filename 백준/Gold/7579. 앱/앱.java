import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int M;
    public static int[][] app;
    public static int[][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        app = new int[N+1][2];
        
        //앱의 메모리
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            app[i][0] = Integer.parseInt(st.nextToken());
        }
        
        
        int mSum = 0;
        int cSum = 0;
        
        //앱의 비활성화 비용
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            app[i][1] = Integer.parseInt(st.nextToken());
            cSum += app[i][1];
        }
        
        
        memo = new int[N+1][cSum+1];
        
        //비용 0일때 초기 메모이제이션 배열 처리
        for(int i=1; i<N+1; i++) {
        	 if(app[i][1] == 0) {
             	mSum += app[i][0];
             	memo[i][0] = mSum;
             }
        }
        

        
        for(int i=1; i<=N; i++) {
        	for(int w=1; w<=cSum ; w++) {
        		int a = memo[i-1][w];
        		memo[i][w] = a;
        		if(app[i][1] <= w) {
        			int b = memo[i-1][w-app[i][1]] + app[i][0];
        			memo[i][w] = Math.max(b, memo[i][w]);
        		}
        	}
        }
        
        
        for(int i=0; i<cSum+1; i++) {
        	if(memo[N][i]>=M) {
        		System.out.println(i);
        		break;
        	}
        }
       
        
        
    }
}