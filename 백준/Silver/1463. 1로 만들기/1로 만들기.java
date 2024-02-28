import java.util.Scanner;

public class Main {
	public static int[] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		memo = new int[n+1];
		if(n == 1) {
			System.out.println(0);
			System.exit(0);
		} else if(n == 2 || n ==3) {
			System.out.println(1);
			System.exit(0);
		}
		memo[1] = 0;
		for(int i=2; i<=3; i++) {
			memo[i] = 1;
		}
		
		System.out.println(dp(n));;
	}
	public static int dp(int n) {
		if(n>=4 && memo[n]==0) {
			if(n % 6 == 0) {
				int min = Math.min(dp(n/2), dp(n/3));
				int m2 = Math.min(dp(n-1), min);
				memo[n] = m2+1;
			}
			else if(n % 3 == 0) {
				memo[n] = Math.min(dp(n/3), dp(n-1)) + 1;
				
			} else if(n % 2 == 0) {
				memo[n] = Math.min(dp(n/2), dp(n-1)) + 1;
				
			} else {
				memo[n] = dp(n-1) + 1;
			}
		}
		return memo[n];
	}
}