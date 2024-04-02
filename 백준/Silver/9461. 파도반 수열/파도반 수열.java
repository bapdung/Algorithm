import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author 서지흔
 * @date 2024. 04. 02.
 * @link https://www.acmicpc.net/problem/9461
 * @keyword_solution 
 *  P(n)=P(n-1)+P(n-5)
 *  P(n)=P(n-2)+P(n-3)
 *  값이 커질 수 있으니 long 배열
 * @input
 * @output 1<=n<=100
 * @time_complex O(n)
 * @perf 
 */
public class Main {
	
	public static long[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		dp = new long[101];
		
		//초기값 세팅
		//P(1)=P(2)=P(3)=1
		//P(4)=P(5)=2
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		dp[4] = 2;
		dp[5] = 2;
		
		//테스트 케이스만큼 반복
		for(int t=1; t<=TC; t++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(topDown(n) + "\n"); //P(n)
		}
		System.out.println(sb);
	}
	
	//Top-down
	public static long topDown(int a) {
		if(a>5 && dp[a]==0) {
			dp[a] = topDown(a-1) + topDown(a-5);
		}
		return dp[a];
	}
	
	//Bottom-up
	public static long bottomUp(int n) {
		for(int i=6; i<=n; i++) {
			dp[i] = dp[i-1] + dp[i-5];
		}
		return dp[n];
	}
}