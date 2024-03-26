import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int W;
	public static int[][] memo;
	public static int[][] bag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		memo = new int[N+1][W+1];
		bag = new int[N+1][2];
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			bag[i][0] = Integer.parseInt(st.nextToken()); //무게
			bag[i][1] = Integer.parseInt(st.nextToken()); //가치
		}
		
		int max = dp(N, W);
		System.out.println(max);
		
		
	}
	public static int dp(int n, int w) {
		if(n>=1 && w>=1 && memo[n][w]==0) {
			int a = dp(n-1, w);
			if(w>=bag[n][0]) {
				int b = dp(n-1, w-bag[n][0]) + bag[n][1];
				memo[n][w] = Math.max(a,b);
			} else {
				memo[n][w] = a;
			}
		}
		return memo[n][w];
		
	}
}