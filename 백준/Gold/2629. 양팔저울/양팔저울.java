import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int CHU;
	public static int[] chu;
	public static int N;
	public static int[] weight;
	public static boolean[][] memo;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		CHU = Integer.parseInt(br.readLine());
		chu = new int[CHU+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<CHU+1 ; i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}
		N = Integer.parseInt(br.readLine());
		weight = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		memo = new boolean[CHU+1][40001];
		dp(0,0);
		for(int i=0; i<N; i++) {
			if(N>15000) {
				sb.append("N ");
			}
			if(memo[CHU][weight[i]]) {
				sb.append("Y ");
			}else {
				sb.append("N ");
			}
		}
		System.out.println(sb);
		
	}
	public static void dp(int i, int w) {
		
		if(i<=CHU) {
			if(memo[i][w]) return;
			memo[i][w] = true;
		}
		if(i>=CHU) return;
		dp(i+1,w + chu[i+1]);
		dp(i+1, w);
		dp(i+1, Math.abs(chu[i+1] - w));
		
	}
}