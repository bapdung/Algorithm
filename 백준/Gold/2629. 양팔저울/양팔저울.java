import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] chu;
	public static int CHU;
	public static int N;
	public static boolean [][] memo;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		CHU = Integer.parseInt(br.readLine());
		chu = new int[CHU+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<CHU; i++) {
			chu[i] = Integer.parseInt(st.nextToken());
		}
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		memo = new boolean[CHU+1][15001];
		dp(0,0);
		for(int i=1; i<N+1; i++) {
			int target = Integer.parseInt(st.nextToken());
			if(target>15000) sb.append("N ");
			else if(memo[CHU][target]) sb.append("Y ");
			else sb.append("N ");
		}
		System.out.println(sb);
		
	}
	public static void dp(int i, int w) {
		if(i>CHU || memo[i][w]) return;
//		System.out.println(i + " " + w);
		memo[i][w] = true;
		dp(i+1, w + chu[i]); //추 올린경우
		dp(i+1, w); //추 안올림
		dp(i+1, Math.abs(w - chu[i])); //추 구슬과 같이 올림
		
	}
}