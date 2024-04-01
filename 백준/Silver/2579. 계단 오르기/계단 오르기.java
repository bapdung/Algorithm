import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		for(int i=1; i<N+1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int[][] dp = new int[N+1][3];
		if(N>=1) {
			dp[1][1] = arr[1];
		} 
		if(N>=2) {
			dp[2][1] = arr[2];
			dp[2][2] = arr[1] + arr[2];
		}
		if(N==1) {
			System.out.println(dp[1][1]);
		}else if(N==2) {
			System.out.println(Math.max(dp[2][1],dp[2][2]));
		} else {
			for(int n=3; n<=N; n++) {
				for(int i=0; i<3; i++) {
					if(i==2) {
						if(dp[n-1][i-1]!=0) {
							dp[n][i] = Math.max(dp[n][i],dp[n-1][i-1] + arr[n]);
						}
					} else if(i==1){
						if(dp[n-2][0]!=0) {
							dp[n][i] = Math.max(dp[n-2][0] + arr[n], dp[n][i]);
						}
						if(dp[n-2][1]!=0) {
							dp[n][i] = Math.max(dp[n-2][1] + arr[n], dp[n][i]);
						}
						if(dp[n-2][2]!=0) {
							dp[n][i] = Math.max(dp[n-2][2] + arr[n], dp[n][i]);
						}
					}
				}
			}
			
			int max = 0;
			for(int i=0; i<3; i++) {
				max = Math.max(dp[N][i], max);
			}
			System.out.println(max);
		}
	}
}