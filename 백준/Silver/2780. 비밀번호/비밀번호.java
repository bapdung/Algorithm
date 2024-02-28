import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			int n = Integer.parseInt(br.readLine());
			dp = new int[n + 1][10];
			long total = 0;

			for (int i = 0; i < 10; i++) {
				dp[1][i] = 1;
			}
			for (int idx = 0; idx < 10; idx++) {
				total += recur(n, idx);
			}
			sb.append(total % 1234567 + "\n");
		}
		System.out.println(sb);
	}

	public static int recur(int n, int idx) {
		if (n != 1 && dp[n][idx] == 0) {
			if (idx == 0) {
				dp[n][idx] = recur(n - 1, 7);
			} else if (idx == 1) {
				dp[n][idx] = recur(n - 1, 2) + recur(n - 1, 4);
			} else if (idx == 2) {
				dp[n][idx] = recur(n - 1, 1) + recur(n - 1, 5) + recur(n - 1, 3);
			} else if (idx == 3) {
				dp[n][idx] = recur(n - 1, 2) + recur(n - 1, 6);
			} else if (idx == 4) {
				dp[n][idx] = recur(n - 1, 1) + recur(n - 1, 5) + recur(n - 1, 7);
			} else if (idx == 5) {
				dp[n][idx] = recur(n - 1, 2) + recur(n - 1, 4) + recur(n - 1, 6) + recur(n - 1, 8);
			} else if (idx == 6) {
				dp[n][idx] = recur(n - 1, 3) + recur(n - 1, 5) + recur(n - 1, 9);
			} else if (idx == 7) {
				dp[n][idx] = recur(n - 1, 4) + recur(n - 1, 8) + recur(n - 1, 0);
			} else if (idx == 8) {
				dp[n][idx] = recur(n - 1, 5) + recur(n - 1, 7) + recur(n - 1, 9);
			} else {
				dp[n][idx] = recur(n - 1, 6) + recur(n - 1, 8);
			}
			dp[n][idx] %= 1234567;

		}
		return dp[n][idx];
	}
}