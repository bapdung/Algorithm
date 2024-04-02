import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static long[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		dp = new long[101];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		for(int t=1; t<=TC; t++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(recur(n) + "\n");
		}
		System.out.println(sb);
	}
	public static long recur(int a) {
		if(a>5 && dp[a]==0) {
			dp[a] = recur(a-1) + recur(a-5);
		}
		return dp[a];
	}
}